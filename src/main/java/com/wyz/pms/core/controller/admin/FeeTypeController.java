package com.wyz.pms.core.controller.admin;

import com.wyz.pms.common.util.PMSUtil;
import com.wyz.pms.common.util.Result;
import com.wyz.pms.core.pojo.FeeType;
import com.wyz.pms.core.service.FeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;


/**
 *  @author: PUING
 *  @Date: 2020/12/7 21:03
 *  @Description: 收费类型控制器
 */
@RequestMapping("/admin/feeType")
@Controller
public class FeeTypeController {

    @Autowired
    private FeeTypeService feeTypeService;

    @RequestMapping("/list")
    public String find(FeeType feeType,Integer sort, Model model){
        List<FeeType> list = feeTypeService.find(feeType,sort);
        list.forEach(System.out::println);
        model.addAttribute("feeTypeList", list);
        return "/manager/fee-type-list.html";
    }

    @RequestMapping("/doAdd")
    public String doAdd() {
        return "/manager/fee-type-add.html";
    }

    @RequestMapping("/doUpdate")
    public String doUpdate(Integer id, Model model) {
        FeeType feeType = feeTypeService.findById(id);
        model.addAttribute("feeType", feeType);
        return "/manager/fee-type-edit.html";
    }

    @ResponseBody
    @RequestMapping("/add")
    public Result add(@Valid FeeType feeType) {
        return PMSUtil.result(feeTypeService.insert(feeType),"添加");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result update(@Valid FeeType feeType){
        return PMSUtil.result(feeTypeService.update(feeType),"修改");
    }


    @ResponseBody
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        return PMSUtil.result(feeTypeService.delete(id), "删除");
    }
}
