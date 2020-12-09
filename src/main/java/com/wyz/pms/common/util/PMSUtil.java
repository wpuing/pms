package com.wyz.pms.common.util;


import com.wyz.pms.common.exception.ParameterException;
import com.wyz.pms.common.exception.PermissionException;
import com.wyz.pms.core.pojo.House;
import com.wyz.pms.core.pojo.Owner;
import com.wyz.pms.core.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: PUING
 * @Date: 2020/12/5 12:38
 * @Description: 验证更新操作
 */

public class PMSUtil {


    public static Result result(int code, String object) {
        if (code > 0) {//成功
            return ResultGenerator.genSuccessResult();
        } else {//失败
            return ResultGenerator.genFailResult(object + "失败！");
        }
    }

    public static String window(String url, Result result) {
        String inScript="<script>";
        String upScript="</script>";
        String src = "window.location.href='"+url+"';";
        String alert = "alert('响应码：" + result.getCode() + " ,消息：" + result.getMessage() + "');";
        return inScript+alert+src+upScript;
    }
    public static LocalDateTime parseLDT(String source) {

        return parseLDT(source,"yyyy-MM-dd");
    }

    public static LocalDateTime parseLDT(String source, String formatPattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        LocalDate localDate = LocalDate.parse(source, dateTimeFormatter);
        return localDate.atStartOfDay();
    }


    /***
     * 验证用户id和状态的合法性
     * @param value1
     * @param value2
     * @param flag
     */
    public static void clickStatusAnOwnerId(Integer value1,Integer value2,boolean flag){
        if(flag){//值1为用户id，值2为状态
            if(value1!=null && value1>0){//存在用户id
                if(value2!=null && value2==1){//设置为未
                    throw new ParameterException("用户id为："+value1+" 状态为未，不合法，失败！");
                }
            }
        }
        if(!flag){//值1为状态，值2为用户id
            if(value1!=null && value1==2){
                if(value2==null || value2<=0){
                    throw new ParameterException("售出状态为：已，用户id为："+value2+"不合法，失败！");
                }
            }
        }

    }
    
}
