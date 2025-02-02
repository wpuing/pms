package com.wyz.pms.core.service;


import com.wyz.pms.core.pojo.Fee;
import com.wyz.pms.core.pojo.vo.FeeDetail;
import com.wyz.pms.core.pojo.vo.FeeTypeMoneyVo;
import com.wyz.pms.core.pojo.vo.FeeVo;

import java.math.BigDecimal;
import java.util.List;

/**
 *  @author: PUING
 *  @Date: 2020/12/8 11:24
 *  @Description: 车位业务
 */
public interface FeeService {

    FeeVo findById(Integer id);

    List<FeeVo> find(String startTime, String endTime, BigDecimal startPrice, BigDecimal endPrice, Fee fee, Integer sort);

    List<FeeDetail> find (Integer feeTypeId,Integer ownerId,Integer status);

    List<FeeTypeMoneyVo> find(String startTime, String endTime, Integer feeTypeId, Integer ownerId);

    int insert(Fee fee);

    int update(Fee fee);

    int delete(Integer id);
    
}
