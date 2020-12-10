package com.wyz.pms.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wyz.pms.core.pojo.Fee;
import com.wyz.pms.core.pojo.vo.FeeDetail;
import com.wyz.pms.core.pojo.vo.FeeTypeMoneyVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeMapper extends BaseMapper<Fee> {

    List<FeeTypeMoneyVo> selectFeeTypeMoney(@Param(Constants.WRAPPER) Wrapper<Fee> wrapper);

    List<FeeDetail> selectFeeDetailByOwner(@Param(Constants.WRAPPER) Wrapper<Fee> wrapper);

}