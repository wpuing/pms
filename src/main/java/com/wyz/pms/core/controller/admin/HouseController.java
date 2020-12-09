package com.wyz.pms.core.controller.admin;

import com.wyz.pms.common.util.PMSUtil;
import com.wyz.pms.common.util.Result;
import com.wyz.pms.core.pojo.House;
import com.wyz.pms.core.pojo.Parking;
import com.wyz.pms.core.pojo.vo.HouseVo;
import com.wyz.pms.core.pojo.vo.ParkingVo;
import com.wyz.pms.core.service.HouseService;
import com.wyz.pms.core.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/admin/house")
@Controller
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("/list")
    public String find(BigDecimal startArea, BigDecimal endArea, House house, Integer sort, Model model){
        List<HouseVo> list = houseService.find(startArea,endArea,house,sort);
        model.addAttribute("houseList", list);
        return "/manager/house-list.html";
    }

    @RequestMapping("/doAdd")
    public String doAdd() {
        return "/manager/house-add.html";
    }

    @RequestMapping("/doUpdate")
    public String doUpdate(Integer id, Model model) {
        HouseVo houseVo = houseService.findById(id);
        model.addAttribute("house", houseVo);
        return "/manager/house-edit.html";
    }

    @ResponseBody
    @RequestMapping("/add")
    public Result add(@Valid House house) {
        return PMSUtil.result(houseService.insert(house),"添加");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result update(@Valid House house){
        return PMSUtil.result(houseService.update(house),"修改");
    }


    @ResponseBody
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        return PMSUtil.result(houseService.delete(id), "删除");
    }
}
