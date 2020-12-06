package com.wyz.pms.core.service;


import com.wyz.pms.core.pojo.Facility;

import java.util.List;

/**
 *  @author: PUING
 *  @Date: 2020/12/6 21:24
 *  @Description: 设施业务
 */
public interface FacilityService {
    
    List<Facility> find(String startTime, String endTime, Facility facility, Integer sort);

    Facility findById(Integer id);

    int insert(Facility facility);

    int update(Facility facility);

    int delete(Integer id);
}
