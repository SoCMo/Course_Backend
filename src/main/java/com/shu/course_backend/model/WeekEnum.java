package com.shu.course_backend.model;

/**
 * @ClassName: WeekEnum
 * @Description: 星期枚举类
 * @Author: pongshy
 * @Date: 2021/5/14-14:52
 * @Version: V1.0
 **/
public enum WeekEnum {

    Mon(1, "周一"),
    Tue(2, "周二"),
    Wed(3, "周三"),
    Thu(4, "周四"),
    Fri(5, "周五"),
    Sat(6, "周六"),
    Sun(7, "周日");


    private Integer i;

    private String week;

    WeekEnum(Integer i, String week) {
        this.i = i;
        this.week = week;
    }

    public static String getWeek(Integer i) {
        for (WeekEnum weekEnum : WeekEnum.values()) {
            if (weekEnum.i == i) {
                return weekEnum.week;
            }
        }
        return null;
    }

    public static Integer getI(String week) {
        for (WeekEnum weekEnum : WeekEnum.values()) {
            if (weekEnum.week.equals(week)) {
                return weekEnum.i - 1;
            }
        }
        return null;
    }


}
