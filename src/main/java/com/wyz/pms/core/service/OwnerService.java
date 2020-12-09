package com.wyz.pms.core.service;


import com.wyz.pms.core.pojo.Owner;

import java.util.List;

/**
 *  @author: PUING
 *  @Date: 2020/12/6 15:04
 *  @Description: 业主业务
 */
public interface OwnerService {

    Owner findById(Integer id);

    Owner findByPhone(String phone,String password);

    Owner findByName(String name);

    List<Owner> find(String startTime,String endTime,Owner owner,Integer sort);

    int insert(Owner owner);

    int update(Owner owner);

    int delete(Integer id);




}
