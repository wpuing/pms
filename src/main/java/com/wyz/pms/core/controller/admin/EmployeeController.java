package com.wyz.pms.core.controller.admin;

import com.wyz.pms.common.util.PMSUtil;
import com.wyz.pms.common.util.Result;
import com.wyz.pms.core.pojo.Employee;
import com.wyz.pms.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: PUING
 * @Date: 2020/12/1 22:34
 * @Description: 员工控制类
 */
@RequestMapping("/admin/emp")
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/list")
    public String find(String startTime,String endTime,Employee employee,Integer sort, Model model){
        List<Employee> list = employeeService.find(startTime,endTime,employee,sort);
        model.addAttribute("empList", list);
        return "/manager/emp-list.html";
    }

    @RequestMapping("/doAdd")
    public String doAdd() {
        return "/manager/emp-add.html";
    }

    @RequestMapping("/doUpdate")
    public String doUpdate(Integer id, Model model) {
        Employee emp = employeeService.findById(id);
        model.addAttribute("emp", emp);
        return "/manager/emp-edit.html";
    }

    @RequestMapping("/doUpdatePassword/{id}")
    public String doUpdatePassword(@PathVariable("id") Integer id, Model model) {
        Employee emp = employeeService.findById(id);
        model.addAttribute("emp", emp);
        return "/manager/change-password.html";
    }
    @RequestMapping("/myInfo/{id}")
    public String myInfo(@PathVariable("id") Integer id, Model model) {
        Employee emp = employeeService.findById(id);
        model.addAttribute("emp", emp);
        return "/manager/myInfo.html";
    }

    @ResponseBody
    @RequestMapping("/add")
    public Result add(Employee employee) {
        return PMSUtil.result(employeeService.insert(employee),"添加");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result update(Employee employee){
        return PMSUtil.result(employeeService.update(employee),"修改");
    }



    @ResponseBody
    @RequestMapping("/updatePassword")
    public Result updatePassword(Employee employee){
        return PMSUtil.result(employeeService.updatePassword(employee),"修改密码");
    }


    @ResponseBody
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        return PMSUtil.result(employeeService.delete(id), "删除");
    }
}
