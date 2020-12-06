package com.wyz.pms.core.service.impl;

import com.wyz.pms.core.pojo.Facility;
import com.wyz.pms.core.service.FacilityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {
    @Override
    public List<Facility> find(String startTime, String endTime, Facility facility, Integer sort) {
        return null;
    }

    @Override
    public Facility findById(Integer id) {
        return null;
    }

    @Override
    public int insert(Facility facility) {
        return 0;
    }

    @Override
    public int update(Facility facility) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }
}
