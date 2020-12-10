package com.wyz.pms.core.controller;

import com.wyz.pms.common.util.PUINGUtil;
import com.wyz.pms.common.util.Result;
import com.wyz.pms.common.util.ResultGenerator;
import com.wyz.pms.core.pojo.Owner;
import com.wyz.pms.core.service.EmployeeService;
import com.wyz.pms.core.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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

    @RequestMapping("/index")
    public String index() {

        return "/manager/index.html";
    }

    @RequestMapping("/login")
    public String login() {

        return "/login.html";
    }

    @RequestMapping("/doLogin")
    public Result doLogin(String username, String password, String role, HttpServletRequest request, HttpServletResponse response) {
        if(!PUINGUtil.isEmpty(role)){
            return ResultGenerator.genFailResult("请勾选用户类型！");
        }
        if(!PUINGUtil.isEmpty(username) || !PUINGUtil.isEmpty(password) ){
            return ResultGenerator.genFailResult("请输入完整信息！");
        }
        if(role.equals("ADMIN")){//管理员

        }
        if(role.equals("OWNER")){//业主
            Owner byName = ownerService.findByName(username);
        }

        return ResultGenerator.genSuccessResult("登录成功！");
    }


}
