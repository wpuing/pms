package com.wyz.pms.core.controller.admin;

import com.wyz.pms.common.util.PMSUtil;
import com.wyz.pms.common.util.Result;
import com.wyz.pms.core.pojo.Employee;
import com.wyz.pms.core.pojo.Owner;
import com.wyz.pms.core.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 *  @author: PUING
 *  @Date: 2020/12/6 15:03
 *  @Description: 业主控制器
 */
@RequestMapping("/owner")
@Controller
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @RequestMapping("/list")
    public String find(String startTime, String endTime, Owner owner,Integer sort, Model model){
        List<Owner> list = ownerService.find(startTime,endTime,owner,sort);
        model.addAttribute("ownerList", list);
        return "/manager/owner-list.html";
    }

    @RequestMapping("/doAdd")
    public String doAdd() {
        return "/manager/owner-add.html";
    }

    @RequestMapping("/doUpdate")
    public String doUpdate(Integer id, Model model) {
        Owner owner = ownerService.findById(id);
        model.addAttribute("owner", owner);
        return "/manager/owner-edit.html";
    }

    @ResponseBody
    @RequestMapping("/add")
    public Result add(Owner owner) {
        return PMSUtil.result(ownerService.insert(owner),"添加");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result update(Owner owner){
        return PMSUtil.result(ownerService.update(owner),"修改");
    }


    @ResponseBody
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        return PMSUtil.result(ownerService.delete(id), "删除");
    }
}
