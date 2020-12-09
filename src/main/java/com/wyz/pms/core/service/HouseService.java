package com.wyz.pms.core.service;


import com.wyz.pms.core.pojo.House;
import com.wyz.pms.core.pojo.vo.HouseVo;

import java.math.BigDecimal;
import java.util.List;

/**
 *  @author: PUING
 *  @Date: 2020/12/8 11:24
 *  @Description: 车位业务
 */
public interface HouseService {

    HouseVo findById(Integer id);

    List<HouseVo> find(BigDecimal startArea, BigDecimal endArea, House house, Integer sort);

    int insert(House house);

    int update(House house);

    int delete(Integer id);


}
