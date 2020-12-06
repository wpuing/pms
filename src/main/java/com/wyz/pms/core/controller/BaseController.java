package com.wyz.pms.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *  @author: PUING
 *  @Date: 2020/12/6 15:44
 *  @Description: 基础
 */
@Controller
public class BaseController {

    @RequestMapping("/index")
    public String index() {

        return "/manager/index.html";
    }


}
