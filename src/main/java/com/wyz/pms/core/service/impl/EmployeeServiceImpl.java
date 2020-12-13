package com.wyz.pms.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wyz.pms.common.exception.ParameterException;
import com.wyz.pms.common.util.PUINGUtil;
import com.wyz.pms.core.mapper.EmployeeMapper;
import com.wyz.pms.core.pojo.Employee;
import com.wyz.pms.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int insert(Employee employee) {
        employee.setCreateTime(LocalDateTime.now());
        employee.setDeleted(0);
        Employee employee1 = findByAccount(employee.getAccount());
        if (!PUINGUtil.isMobile(employee.getPhone())) {
            throw new ParameterException("员工管理： 员工手机号错误或者格式错误， 参数：" + employee.getPhone());
        }
        if (employee1!=null){
            throw new ParameterException("该编号所属用户已存在！！！ 编号："+employee.getAccount());
        }
        return employeeMapper.insert(employee);
    }

    @Override
    public int update(Employee employee) {
        if (!PUINGUtil.isMobile(employee.getPhone())) {
            throw new ParameterException("员工管理： 员工手机号错误或者格式错误， 参数：" + employee.getPhone());
        }
        Employee employee1 = findByAccount(employee.getAccount());
        if (employee1!=null && employee1.getId()!=employee.getId()){
            //当查询出该编号号存在员工，且id与要修改的员工id不一致则为重复编号
            throw new ParameterException("该编号所属用户已存在！！！ 编号id："+employee.getId());
        }
        return employeeMapper.updateById(employee);
    }

    @Override
    public int updatePassword(Employee employee) {
        if(employee!=null){
            Employee employee1 = findById(employee.getId());
            if(employee1!=null){
                if (PUINGUtil.isEmpty(employee.getPassword())) {//密码
                    employee1.setPassword(employee.getPassword());
                }
                return employeeMapper.updateById(employee1);
            }
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        PUINGUtil.notNullByZero(id, "员工编号不能为空或者小于等于0");
        return employeeMapper.deleteById(id);
    }

    @Override
    public Employee findById(Integer id) {
        PUINGUtil.notNullByZero(id, "员工编号不能为空或者小于等于0");
        return employeeMapper.selectById(id);
    }

    @Override
    public Employee find(String account, String password) {
        PUINGUtil.isEmpty("员工管理：账号不能为空！！！",account);
        PUINGUtil.isEmpty("员工管理：密码不能为空！！！",password);
        LambdaQueryWrapper<Employee> wrapper = Wrappers.<Employee>lambdaQuery();
        Employee employee = null;
        List<Employee> list = employeeMapper.selectList(wrapper);
        if(list==null || list.size()<=0){
            throw new ParameterException("该账号查询不到员工信息：null ,账号:"+account);
        }
        for (Employee e:list) {
            if(e.getPassword().equals(password)&&e.getAccount().equals(account)){
                employee=e;
            }
        }
        return employee;
    }


    @Override
    public List<Employee> find(String startTime, String endTime, Employee employee,Integer sort) {
        LambdaQueryWrapper<Employee> wrapper = Wrappers.<Employee>lambdaQuery();
        if (employee != null) {
            if (PUINGUtil.isEmpty(employee.getAccount())) {//账号
                wrapper.like(Employee::getAccount, employee.getAccount());
            }
            if (PUINGUtil.isEmpty(employee.getName())) {//姓名
                wrapper.like(Employee::getName, employee.getName());
            }
            if (PUINGUtil.isEmpty(employee.getSex())) {//性别
                wrapper.like(Employee::getSex, employee.getSex());
            }
            if (PUINGUtil.isEmpty(employee.getAge())) {//年龄
                wrapper.like(Employee::getAge, employee.getAge());
            }
            if (PUINGUtil.isEmpty(employee.getPhone())) {//手机号
                wrapper.like(Employee::getPhone, employee.getPhone());
            }
            if (employee.getRoleId()!=null && employee.getRoleId()>=0) {//角色
                wrapper.eq(Employee::getRoleId, employee.getRoleId());
            }
        }
        if(PUINGUtil.isEmpty(startTime)){//开始
            wrapper.apply("date_format(create_time,'%Y-%m-%d')>={0}", startTime);
        }
        if(PUINGUtil.isEmpty(endTime)){//结束
            wrapper.apply("date_format(create_time,'%Y-%m-%d')<={0}", endTime);
        }
        if(sort!=null && sort==1){//排序
            wrapper.orderByAsc(Employee::getId);
        }else {
            wrapper.orderByDesc(Employee::getId);
        }
        return employeeMapper.selectList(wrapper);
    }

    private Employee findByAccount(String account){
        LambdaQueryWrapper<Employee> wrapper = Wrappers.<Employee>lambdaQuery();
        wrapper.eq(Employee::getAccount,account);
        List<Employee> list = employeeMapper.selectList(wrapper);
        if(list!=null && list.size()>0){
            return list.get(0);//存在则取第一条
        }
        return null;
    }

}
