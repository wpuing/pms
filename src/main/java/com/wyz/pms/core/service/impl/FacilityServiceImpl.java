package com.wyz.pms.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wyz.pms.common.util.PUINGUtil;
import com.wyz.pms.core.mapper.FacilityMapper;
import com.wyz.pms.core.pojo.Facility;
import com.wyz.pms.core.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityMapper facilityMapper;

    @Override
    public List<Facility> find(String startTime, String endTime, Facility facility, Integer sort) {
        LambdaQueryWrapper<Facility> wrapper = Wrappers.<Facility>lambdaQuery();
        if (facility != null) {
            if (PUINGUtil.isEmpty(facility.getName())) {//名称
                wrapper.like(Facility::getName, facility.getName());
            }
            if (PUINGUtil.isEmpty(facility.getSite())) {//地点
                wrapper.like(Facility::getSite, facility.getSite());
            }
            if (facility.getCount() != null && facility.getCount() > 0) {//数量
                wrapper.eq(Facility::getCount, facility.getCount());
            }
        }
        if (PUINGUtil.isEmpty(startTime)) {//开始
            wrapper.apply("date_format(start_time,'%Y-%m-%d')>={0}", startTime);
        }
        if (PUINGUtil.isEmpty(endTime)) {//结束
            wrapper.apply("date_format(start_time,'%Y-%m-%d')<={0}", endTime);
        }
        if (sort != null && sort == 1) {//排序
            wrapper.orderByAsc(Facility::getId);
        } else {
            wrapper.orderByDesc(Facility::getId);
        }
        return facilityMapper.selectList(wrapper);
    }

    @Override
    public Facility findById(Integer id) {
        PUINGUtil.notNullByZero(id, "员工编号不能为空或者小于等于0");
        return facilityMapper.selectById(id);
    }

    @Override
    public int insert(Facility facility) {
        if (facility!=null && facility.getStatus()!=null
                && PUINGUtil.isEmpty(facility.getStatus()) && facility.getStatus().equals("已使用")) {
            facility.setStartTime(LocalDateTime.now());
        }
        return facilityMapper.insert(facility);
    }

    @Override
    public int update(Facility facility) {
        //当使用时间为空并且设置为已使用才会设置使用时间
        if (facility != null && facility.getStartTime()==null&&facility.getStatus().equals("已使用")) {
            facility.setStartTime(LocalDateTime.now());
        }
        //设置为空
        if (facility != null && facility.getStartTime()!=null&&facility.getStatus().equals("未使用")) {
            facility.setStartTime(null);
        }
        return facilityMapper.updateById(facility);
    }

    @Override
    public int delete(Integer id) {
        PUINGUtil.notNullByZero(id, "员工编号不能为空或者小于等于0");
        return facilityMapper.deleteById(id);
    }
}
