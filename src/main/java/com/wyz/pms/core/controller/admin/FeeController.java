package com.wyz.pms.core.controller.admin;

import com.wyz.pms.common.constant.PMSConstant;
import com.wyz.pms.common.util.PMSUtil;
import com.wyz.pms.common.util.PUINGUtil;
import com.wyz.pms.common.util.Result;
import com.wyz.pms.core.pojo.Fee;
import com.wyz.pms.core.pojo.FeeType;
import com.wyz.pms.core.pojo.Repair;
import com.wyz.pms.core.pojo.vo.*;
import com.wyz.pms.core.service.FeeService;
import com.wyz.pms.core.service.FeeTypeService;
import com.wyz.pms.core.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin/fee")
@Controller
public class FeeController {

    @Autowired
    private FeeService feeService;

    @Autowired
    private FeeTypeService feeTypeService;

    @RequestMapping("/list")
    public String find(String startTime, String endTime, BigDecimal startPrice, BigDecimal endPrice, Fee fee, Integer sort, Model model) {
        List<FeeVo> list = feeService.find(startTime, endTime, startPrice, endPrice, fee, sort);
        List<FeeType> feeTypes = feeTypeService.find(null, 1);
        model.addAttribute("feeTypeList", feeTypes);
        model.addAttribute("feeList", list);
        return "/manager/fee-list.html";
    }

    @RequestMapping("/ownerMoney")
    public String find(String startTime, String endTime, Integer feeTypeId, Integer ownerId, Model model) {
        List<FeeTypeMoneyVo> list = feeService.find(startTime, endTime, feeTypeId, ownerId);
        List<FeeType> feeTypes = feeTypeService.find(null, 1);
        model.addAttribute("feeTypeList", feeTypes);
        model.addAttribute("feeTypeMoneyList", list);
        return "/manager/fee-type-money-list.html";
    }

    @RequestMapping("/feeDetailByOwnerIdAndType/{feeTypeId}/{ownerId}")
    public String find(@PathVariable("feeTypeId") Integer feeTypeId,@PathVariable("ownerId") Integer ownerId, Model model) {
        List<FeeDetail> list1 = feeService.find( feeTypeId, ownerId,1);
        List<FeeDetail> list2 = feeService.find( feeTypeId, ownerId,2);
        List<FeeDetailVo> detailVos = new ArrayList<>();
        detailVos.add(new FeeDetailVo(PMSConstant.NOT_FEE,list1));
        detailVos.add(new FeeDetailVo(PMSConstant.FEE,list2));
        model.addAttribute("detailListVos", detailVos);
        return "/manager/fee-detail.html";
    }

    @RequestMapping("/doAdd")
    public String doAdd(Model model) {
        List<FeeType> feeTypes = feeTypeService.find(null, 1);
        model.addAttribute("feeTypeList", feeTypes);
        return "/manager/fee-add.html";
    }

    @RequestMapping("/doUpdate")
    public String doUpdate(Integer id, Model model) {
        List<FeeType> feeTypes = feeTypeService.find(null, 1);
        model.addAttribute("feeTypeList", feeTypes);
        FeeVo feeVo = feeService.findById(id);
        model.addAttribute("fee", feeVo);
        return "/manager/fee-edit.html";
    }




    @ResponseBody
    @RequestMapping("/add")
    public Result add(@Valid  Fee fee) {
        return PMSUtil.result(feeService.insert(fee), "添加");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result update(@Valid  Fee fee) {
        return PMSUtil.result(feeService.update(fee), "修改");
    }


    @ResponseBody
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        return PMSUtil.result(feeService.delete(id), "删除");
    }
}
