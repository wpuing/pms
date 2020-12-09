package com.wyz.pms.core.controller.admin;

import com.wyz.pms.common.util.PMSUtil;
import com.wyz.pms.common.util.Result;
import com.wyz.pms.core.pojo.Parking;
import com.wyz.pms.core.pojo.vo.ParkingVo;
import com.wyz.pms.core.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/admin/parking")
@Controller
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @RequestMapping("/list")
    public String find(BigDecimal startPrice, BigDecimal endPrice, Parking parking, Integer sort, Model model){
        List<ParkingVo> list = parkingService.find(startPrice,endPrice,parking,sort);
        list.forEach(System.out::println);
        model.addAttribute("parkingList", list);
        return "/manager/parking-list.html";
    }

    @RequestMapping("/doAdd")
    public String doAdd() {
        return "/manager/parking-add.html";
    }

    @RequestMapping("/doUpdate")
    public String doUpdate(Integer id, Model model) {
        ParkingVo parkingVo = parkingService.findById(id);
        model.addAttribute("parking", parkingVo);
        return "/manager/parking-edit.html";
    }

    @ResponseBody
    @RequestMapping("/add")
    public Result add(@Valid Parking parking) {
        return PMSUtil.result(parkingService.insert(parking),"添加");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result update(@Valid Parking parking){
        return PMSUtil.result(parkingService.update(parking),"修改");
    }


    @ResponseBody
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        return PMSUtil.result(parkingService.delete(id), "删除");
    }
}
