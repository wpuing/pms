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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


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

    @ResponseBody
    @RequestMapping("/doLogin")
    public Result doLogin(String username, String password, String role, HttpServletRequest request, HttpServletResponse response, Model model) {
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
            //return "/manager/index.html";
            return ResultGenerator.genSuccessResult("/admin/index");
        }
        if(role.equals("OWNER")){//业主
            owner = ownerService.findByPhone(username,password);
            isLogin(owner);
            request.getSession().setAttribute("owner",owner);//存入会话
            request.getSession().setAttribute("username",owner.getName());//存入会话
            //return "/owner/index.html";
            return ResultGenerator.genSuccessResult("/owner/index");
        }
        //return "/login.html";
        return ResultGenerator.genSuccessResult("/login");
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


    /**
     * @Title: upload
     * @Description: 单文件上传
     * @param file 文件信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/file/upload")
    public Result upload(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResultGenerator.genFailResult("文件为空");
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 设置文件存储路径
            //String filePath = "D:\\CodeSpace\\Java\\Test\\file\\one_to_pick\\";
            String fName = UUID.randomUUID().toString().replace("-", "");
            //String filePath = "/usr/etc/images/";
            String filePath = "D:\\CodeSpace\\Java\\Test\\image\\";
            String path = filePath + fName + suffixName;
            System.out.println("文件名+后缀："+fileName);
            System.out.println("文件后缀名："+suffixName);
            System.out.println("文件大小："+file.getSize());
            System.out.println("文件路径："+filePath);
            System.out.println("文件最终路径："+path);
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            String imgName = fName + suffixName;
            return ResultGenerator.genSuccessResult(imgName);//名称
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("上传失败");
    }
}
