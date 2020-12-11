package com.wyz.pms.core.controller.owner;

import com.wyz.pms.common.constant.PMSConstant;
import com.wyz.pms.common.util.PMSUtil;
import com.wyz.pms.common.util.Result;
import com.wyz.pms.core.pojo.House;
import com.wyz.pms.core.pojo.Owner;
import com.wyz.pms.core.pojo.Parking;
import com.wyz.pms.core.pojo.vo.FeeDetail;
import com.wyz.pms.core.pojo.vo.FeeDetailVo;
import com.wyz.pms.core.pojo.vo.HouseVo;
import com.wyz.pms.core.pojo.vo.ParkingVo;
import com.wyz.pms.core.service.FeeService;
import com.wyz.pms.core.service.HouseService;
import com.wyz.pms.core.service.OwnerService;
import com.wyz.pms.core.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/owner/base")
@Controller
public class OwnerBaseController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private FeeService feeService;

    @RequestMapping("/index")
    public String index() {

        return "/owner/index.html";
    }

    @RequestMapping("/myInfo/{id}")//个人
    public String myInfo(@PathVariable("id")Integer id, Model model) {
        Owner owner = ownerService.findById(id);
        model.addAttribute("owner", owner);
        return "/owner/myInfo.html";
    }

    @RequestMapping("/myAsset/{id}")//资产
    public String myAsset(@PathVariable("id")Integer id, Model model) {
        Parking parking = new Parking();
        parking.setOwnerId(id);
        List<ParkingVo> parkingVos = parkingService.find(null, null, parking, null);
        House house = new House();
        house.setOwnerId(id);
        List<HouseVo> houseVos = houseService.find(null, null, house, null);
        model.addAttribute("parkingList", parkingVos);
        model.addAttribute("houseList", houseVos);
        return "/owner/myAsset.html";
    }

    @RequestMapping("/myFee/{ownerId}")
    public String myFee(@PathVariable("ownerId") Integer ownerId, Model model) {
        List<FeeDetail> list1 = feeService.find(null,ownerId,1);
        List<FeeDetail> list2 = feeService.find( null,ownerId,2);
        List<FeeDetailVo> detailVos = new ArrayList<>();
        detailVos.add(new FeeDetailVo(PMSConstant.NOT_FEE,list1));
        detailVos.add(new FeeDetailVo(PMSConstant.FEE,list2));
        model.addAttribute("detailListVos", detailVos);
        list1.forEach(System.out::println);
        list2.forEach(System.out::println);
        return "/owner/myFee.html";
    }

    @RequestMapping("/doUpdatePassword/{id}")
    public String doUpdatePassword(@PathVariable("id") Integer id, Model model) {
        Owner owner = ownerService.findById(id);
        model.addAttribute("owner", owner);
        return "/owner/change-password.html";
    }

    @ResponseBody
    @RequestMapping("/updatePassword")
    public Result updatePassword( Owner owner){
        return PMSUtil.result(ownerService.updatePassword(owner),"修改密码");
    }

}
