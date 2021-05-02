package com.shu.course_backend.tool;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: StrUtils
 * @Description: 字符串工具类
 * @Author: pongshy
 * @Date: 2021/5/2-20:21
 * @Version: V1.0
 **/
public class StrUtil {


    public static String semesterConversion(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        String year = str.substring(0, 4);
        String sem = str.substring(4);
        StringBuffer sb = new StringBuffer();
        sb.append(year);
        sb.append("学年");
        switch (sem) {
            case "1": {
                sb.append("秋季学期");
                break;
            }
            case "2": {
                sb.append("冬季学期");
                break;
            }
            case "3": {
                sb.append("春季学期");
                break;
            }
            case "4": {
                sb.append("夏季学期");
                break;
            }
        }
        return sb.toString();
    }
}
