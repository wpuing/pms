package com.wyz.pms.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wyz.pms.core.mapper.EmployeeMapper;
import com.wyz.pms.core.pojo.Employee;
import com.wyz.pms.core.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void insert(){
        Employee emp = new Employee();
        emp.setAccount("dzq");//账号
        emp.setPassword("123456");//密码
        emp.setName("邓紫棋");
        emp.setSex("女");
        emp.setPhone("18377754496");
        emp.setRoleId(3);
        emp.setCreateTime(LocalDateTime.now());
        emp.setDeleted(0);
        int insert = employeeMapper.insert(emp);
        System.out.println("受影响的记录数："+insert);
    }

    @Test
    public void selectById(){
        Integer id = 2;
        Employee employee = employeeMapper.selectById(id);
        System.out.println("查询数据： "+employee);

    }

    @Test
    public void selectList(){
        //startTime
        //endTime
        //account
        //name
        //sex
        //phone
        //roleId
        LambdaQueryWrapper<Employee> wrapper = Wrappers.<Employee>lambdaQuery();
        wrapper.apply("date_format(create_time,'%Y-%m-%d')>={0}", "2020-12-03")
                .apply("date_format(create_time,'%Y-%m-%d')<={0}", "2020-12-05")
                .like(Employee::getAccount,"z")
                .like(Employee::getName,"刘")
               // .eq(Employee::getSex,"")
                .like(Employee::getPhone,"183")
                .eq(Employee::getRoleId,2);
        List<Employee> list = employeeMapper.selectList(wrapper);
        list.forEach(System.out::println);

    }

    @Test
    public void selectIds() {

        List<Integer> ids = Arrays.asList(2, 3, 1, 4);
        List<Employee> list = employeeMapper.selectBatchIds(ids);
        list.forEach(System.out::println);

    }

}
