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
            case "0": {
                sb.append("春季学期");
                break;
            }
            case "1": {
                sb.append("夏季学期");
                break;
            }
            case "2": {
                sb.append("秋季学期");
                break;
            }
            case "3": {
                sb.append("冬季学期");
                break;
            }
        }
        return sb.toString();
    }

    public static String semesterFromStrToInt(String semester) {
        if (StringUtils.isEmpty(semester)) {
            return null;
        }
        // 形如: 2021学年春季学期
        StringBuffer sb = new StringBuffer();
        sb.append(semester.substring(0, 4));
        String season = semester.substring(semester.indexOf("年") + 1);
        switch (season) {
            case "春季学期": {
                sb.append("0");
                break;
            }
            case "夏季学期": {
                sb.append("1");
                break;
            }
            case "秋季学期": {
                sb.append("2");
                break;
            }
            case "冬季学期": {
                sb.append("3");
                break;
            }
            default: {
            }
        }
        return sb.toString();
    }
}
