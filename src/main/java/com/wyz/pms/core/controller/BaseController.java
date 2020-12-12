package com.wyz.pms.core.controller;

import com.wyz.pms.common.exception.ParameterException;
import com.wyz.pms.common.util.PUINGUtil;
import com.wyz.pms.common.util.Result;
import com.wyz.pms.common.util.ResultGenerator;
import com.wyz.pms.core.pojo.Employee;
import com.wyz.pms.core.pojo.Owner;
import com.wyz.pms.core.service.EmployeeService;
import com.wyz.pms.core.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *  @author: PUING
 *  @Date: 2020/12/6 15:44
 *  @Description: 基础
 */
@Controller
public class BaseController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OwnerService ownerService;

    @RequestMapping("/admin/index")
    public String managerIndex() {

        return "/manager/index.html";
    }
    @RequestMapping("/owner/index")
    public String ownerIndex() {

        return "/owner/index.html";
    }

    @RequestMapping("/login")
    public String login() {

        return "/login.html";
    }

    @RequestMapping("/doLogin")
    public String doLogin(String username, String password, String role, HttpServletRequest request, HttpServletResponse response, Model model) {
        Employee employee = null;
        Owner owner = null;
        if(!PUINGUtil.isEmpty(role)){
            throw new ParameterException("请勾选用户类型！");
        }
        if(!PUINGUtil.isEmpty(username) || !PUINGUtil.isEmpty(password) ){
            throw new ParameterException("请输入完整信息！");
        }
        if(role.equals("ADMIN")){//管理员
            employee = employeeService.find(username, password);
            isLogin(employee);
            request.getSession().setAttribute("emp",employee);//存入会话
            request.getSession().setAttribute("username",employee.getName());//存入会话
            return "/manager/index.html";
        }
        if(role.equals("OWNER")){//业主
            owner = ownerService.findByPhone(username,password);
            isLogin(owner);
            request.getSession().setAttribute("owner",owner);//存入会话
            request.getSession().setAttribute("username",owner.getName());//存入会话
            return "/owner/index.html";
        }
        return "/login.html";
    }


    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        HttpSession session=request.getSession();
        session.invalidate();
        return "/login.html";
    }

    private void isLogin(Object object){
        if(object==null){
            throw new ParameterException("登录失败！，信息不存在！");
        }
    }
}
