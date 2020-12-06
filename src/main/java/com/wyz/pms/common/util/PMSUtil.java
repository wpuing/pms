package com.wyz.pms.common.util;


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
    
}
