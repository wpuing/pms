package com.wyz.pms.core.service;

import com.wyz.pms.core.pojo.Employee;

import java.time.LocalDateTime;
import java.util.List;


/**
 *  @author: PUING
 *  @Date: 2020/12/2 21:25
 *  @Description: 员工业务类
 */
public interface EmployeeService {

    int insert(Employee employee);

    int update(Employee employee);

    int updatePassword(Employee employee);

    int delete(Integer id);

    Employee findById(Integer id);

    List<Employee> find(String startTime,String endTime,Employee employee,Integer sort);
}
