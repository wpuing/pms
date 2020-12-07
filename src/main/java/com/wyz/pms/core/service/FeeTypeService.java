package com.wyz.pms.core.service;


import com.wyz.pms.core.pojo.FeeType;

import java.util.List;

/**
 *  @author: PUING
 *  @Date: 2020/12/7 20:04
 *  @Description: 收费类型业务
 */
public interface FeeTypeService {

    FeeType findById(Integer id);

    List<FeeType> find(FeeType feeType, Integer sort);

    int insert(FeeType feeType);

    int update(FeeType feeType);

    int delete(Integer id);


}
