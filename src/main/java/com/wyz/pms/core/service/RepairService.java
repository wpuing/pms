package com.wyz.pms.core.service;


import com.wyz.pms.core.pojo.Repair;
import com.wyz.pms.core.pojo.vo.RepairVo;

import java.util.List;

/**
 *  @author: PUING
 *  @Date: 2020/12/8 11:24
 *  @Description: 车位业务
 */
public interface RepairService {

    RepairVo findById(Integer id);

    List<RepairVo> find(String createStartTime, String createEndTime,
                        String resolveStartTime, String resolveEndTime, Repair repair, Integer sort);

    int insert(Repair repair);

    int update(Repair repair);

    int delete(Integer id);


}
