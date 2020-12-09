package com.wyz.pms.core.service;


import com.wyz.pms.core.pojo.Parking;
import com.wyz.pms.core.pojo.vo.ParkingVo;

import java.math.BigDecimal;
import java.util.List;

/**
 *  @author: PUING
 *  @Date: 2020/12/8 11:24
 *  @Description: 车位业务
 */
public interface ParkingService {

    ParkingVo findById(Integer id);

    List<ParkingVo> find(BigDecimal startPrice,BigDecimal endPrice,Parking parking, Integer sort);

    int insert(Parking parking);

    int update(Parking parking);

    int delete(Integer id);


}
