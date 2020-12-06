package com.wyz.pms.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wyz.pms.common.util.PUINGUtil;
import com.wyz.pms.core.mapper.OwnerMapper;
import com.wyz.pms.core.pojo.Employee;
import com.wyz.pms.core.pojo.Owner;
import com.wyz.pms.core.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerMapper ownerMapper;

    @Override
    public Owner findById(Integer id) {
        PUINGUtil.notNullByZero(id, "员工编号不能为空或者小于等于0");
        return ownerMapper.selectById(id);
    }

    @Override
    public List<Owner> find(String startTime,String endTime,Owner owner,Integer sort) {
        LambdaQueryWrapper<Owner> wrapper = Wrappers.<Owner>lambdaQuery();
        if (owner != null) {
            if (PUINGUtil.isEmpty(owner.getName())) {//姓名
                wrapper.like(Owner::getName, owner.getName());
            }
            if (PUINGUtil.isEmpty(owner.getSex())) {//性别
                wrapper.like(Owner::getSex, owner.getSex());
            }
            if (PUINGUtil.isEmpty(owner.getPhone())) {//手机号
                wrapper.like(Owner::getPhone, owner.getPhone());
            }
        }
        if(PUINGUtil.isEmpty(startTime)){//开始
            wrapper.apply("date_format(create_time,'%Y-%m-%d')>={0}", startTime);
        }
        if(PUINGUtil.isEmpty(endTime)){//结束
            wrapper.apply("date_format(create_time,'%Y-%m-%d')<={0}", endTime);
        }
        if(sort!=null && sort==1){//排序
            wrapper.orderByAsc(Owner::getId);
        }else {
            wrapper.orderByDesc(Owner::getId);
        }
        return ownerMapper.selectList(wrapper);
    }

    @Override
    public int insert(Owner owner) {
        owner.setCreateTime(LocalDateTime.now());
        owner.setDeleted(0);
        return ownerMapper.insert(owner);
    }

    @Override
    public int update(Owner owner) {

        return ownerMapper.updateById(owner);
    }

    @Override
    public int delete(Integer id) {
        PUINGUtil.notNullByZero(id, "员工编号不能为空或者小于等于0");
        return ownerMapper.deleteById(id);
    }
}
