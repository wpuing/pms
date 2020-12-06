package com.wyz.pms.core.controller.admin;

import com.wyz.pms.common.util.PMSUtil;
import com.wyz.pms.common.util.Result;
import com.wyz.pms.core.pojo.Facility;
import com.wyz.pms.core.pojo.Owner;
import com.wyz.pms.core.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 *  @author: PUING
 *  @Date: 2020/12/6 21:24
 *  @Description: 设施控制器
 */
@RequestMapping("/facility")
@Controller
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @RequestMapping("/list")
    public String find(String startTime, String endTime, Facility facility, Integer sort, Model model){
//        List<Facility> list = facilityService.find(startTime,endTime,facility,sort);
//        model.addAttribute("facilityList", list);
        return "/manager/facility-list.html";
    }

    @RequestMapping("/doAdd")
    public String doAdd() {
        return "/manager/facility-add.html";
    }

    @RequestMapping("/doUpdate")
    public String doUpdate(Integer id, Model model) {
        Facility facility = facilityService.findById(id);
        model.addAttribute("facility", facility);
        return "/manager/facility-edit.html";
    }

    @ResponseBody
    @RequestMapping("/add")
    public Result add(Facility facility) {
        return PMSUtil.result(facilityService.insert(facility),"添加");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result update(Facility facility){
        return PMSUtil.result(facilityService.update(facility),"修改");
    }


    @ResponseBody
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        return PMSUtil.result(facilityService.delete(id), "删除");
    }

}
