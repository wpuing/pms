package com.wyz.pms.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wyz.pms.common.exception.ParameterException;
import com.wyz.pms.common.util.PUINGUtil;
import com.wyz.pms.core.mapper.OwnerMapper;
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
    public Owner findByPhone(String phone,String password) {
        Owner owner = new Owner();
        LambdaQueryWrapper<Owner> wrapper = Wrappers.<Owner>lambdaQuery();
        PUINGUtil.isEmpty("业主管理：手机号不能为空！！！",phone);
        PUINGUtil.isEmpty("业主管理：密码不能为空！！！",password);
        wrapper.eq(Owner::getPhone,phone);
        List<Owner> owners = ownerMapper.selectList(wrapper);
        if(owners==null || owners.size()<=0){
            throw new ParameterException("该手机号查询不到业主信息：null ,手机号:"+phone);
        }
        for (Owner o: owners) {
            if(o.getPassword().equals(password)&&o.getPhone().equals(phone)){
                owner=o;
            }
        }
        return owner;
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
            if (PUINGUtil.isEmpty(owner.getAge())) {//年龄
                wrapper.like(Owner::getAge, owner.getAge());
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
        Owner owner1 = findByPhone(owner.getPhone());
        if (owner1!=null){
            throw new ParameterException("该手机号所属用户已存在！！！ 手机号："+owner.getPhone());
        }
        return ownerMapper.insert(owner);
    }

    @Override
    public int update(Owner owner) {
        Owner owner1 = findByPhone(owner.getPhone());
        if (owner1!=null && owner.getId()!=owner1.getId()){
            //当查询出该手机号存在用户，且id与要修改的业主id不一致则为重复手机号
            throw new ParameterException("该手机号所属用户已存在！！！ 编号："+owner1.getId());
        }
        return ownerMapper.updateById(owner);
    }

    @Override
    public int updatePassword(Owner owner) {
        if(owner!=null){
            Owner owner1 = findById(owner.getId());
            if(owner1!=null){
                if (PUINGUtil.isEmpty(owner.getPassword())) {//密码
                    owner1.setPassword(owner.getPassword());
                }
                return ownerMapper.updateById(owner1);
            }
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        PUINGUtil.notNullByZero(id, "员工编号不能为空或者小于等于0");
        return ownerMapper.deleteById(id);
    }


    private Owner findByPhone(String phone) {
        PUINGUtil.isEmpty("业主管理：查询的业主手机号不能为空！！！",phone);
        if (!PUINGUtil.isMobile(phone)) {
            throw new ParameterException("业主管理： 业主手机号错误或者格式错误， 参数：" + phone);
        }
        LambdaQueryWrapper<Owner> wrapper = Wrappers.<Owner>lambdaQuery();
        wrapper.eq(Owner::getPhone,phone);
        List<Owner> list = ownerMapper.selectList(wrapper);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
