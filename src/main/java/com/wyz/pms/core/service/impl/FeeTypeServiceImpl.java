package com.wyz.pms.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wyz.pms.common.util.PUINGUtil;
import com.wyz.pms.core.mapper.FeeTypeMapper;
import com.wyz.pms.core.pojo.FeeType;
import com.wyz.pms.core.service.FeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeTypeServiceImpl implements FeeTypeService {

    @Autowired
    private FeeTypeMapper feeTypeMapper;

    @Override
    public FeeType findById(Integer id) {
        PUINGUtil.notNullByZero(id, "收费类型编号不能为空或者小于等于0");
        return feeTypeMapper.selectById(id);
    }

    @Override
    public List<FeeType> find(FeeType feeType, Integer sort) {
        LambdaQueryWrapper<FeeType> wrapper = Wrappers.<FeeType>lambdaQuery();
        if (feeType != null && PUINGUtil.isEmpty(feeType.getName())) {//名称
            wrapper.like(FeeType::getName, feeType.getName());
        }
        if (feeType != null && PUINGUtil.isEmpty(feeType.getRemark())) {//备注
            wrapper.like(FeeType::getRemark, feeType.getRemark());
        }
        if (sort != null && sort == 1) {//排序
            wrapper.orderByAsc(FeeType::getId);
        } else {
            wrapper.orderByDesc(FeeType::getId);
        }
        return feeTypeMapper.selectList(wrapper);
    }

    @Override
    public int insert(FeeType feeType) {
        return feeTypeMapper.insert(feeType);
    }

    @Override
    public int update(FeeType feeType) {
        return feeTypeMapper.updateById(feeType);
    }

    @Override
    public int delete(Integer id) {
        PUINGUtil.notNullByZero(id, "收费类型编号不能为空或者小于等于0");
        return feeTypeMapper.deleteById(id);
    }
}