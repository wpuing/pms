package com.wyz.pms.core.controller.owner;

import com.wyz.pms.common.util.PMSUtil;
import com.wyz.pms.common.util.PUINGUtil;
import com.wyz.pms.common.util.Result;
import com.wyz.pms.core.pojo.Owner;
import com.wyz.pms.core.pojo.Repair;
import com.wyz.pms.core.pojo.vo.RepairVo;
import com.wyz.pms.core.service.OwnerService;
import com.wyz.pms.core.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/owner/repair")
@Controller
public class OwnerRepairController {

    @Autowired
    private RepairService repairService;


    @RequestMapping("/doList/{ownerId}")
    public String find(@PathVariable("ownerId") Integer ownerId, Model model){
        Repair repair =new Repair();
        repair.setOwnerId(ownerId);
        System.out.println("用户id： "+ownerId);
        return find(null,null,null,null,repair,null,model);
    }


    @RequestMapping("/list")
    public String find(String createStartTime, String createEndTime,
                       String resolveStartTime, String resolveEndTime, Repair repair, Integer sort, Model model) {
        List<RepairVo> list = repairService.find(createStartTime, createEndTime, resolveStartTime, resolveEndTime, repair, sort);
        model.addAttribute("repairList", list);
        list.forEach(System.out::println);
        return "/owner/repair-list.html";
    }

    @RequestMapping("/doAdd")
    public String doAdd() {
        return "/owner/repair-add.html";
    }


    @RequestMapping("/doUpdate")
    public String doUpdate(Integer id, Model model) {
        RepairVo repairVo = repairService.findById(id);
        model.addAttribute("repair", repairVo);
        return "/owner/repair-edit.html";
    }

    @ResponseBody
    @RequestMapping("/add")
    public Result add(@Valid Repair repair,String create) {
        if(PUINGUtil.isEmpty(create)){
            repair.setCreateTime(PMSUtil.parseLDT(create));
        }
        return PMSUtil.result(repairService.insert(repair), "添加");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result update(@Valid Repair repair) {
        return PMSUtil.result(repairService.update(repair), "修改");
    }


    @ResponseBody
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        return PMSUtil.result(repairService.delete(id), "删除");
    }
}
