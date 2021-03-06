package com.shu.course_backend.tool;

import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.WeekEnum;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CourseTool
 * @Description: 课程工具类
 * @Author: pongshy
 * @Date: 2021/5/14-14:48
 * @Version: V1.0
 **/

public class CourseTool {


    /*
     * @Description: 时间转换函数: str->bit
     * @Param: [courseTimeList]
     * @return: java.lang.String
     * @Author: pongshy
     * @Date: 2021/5/14
     * @Version: V1.0
     **/
    public static String translateFromStrToBit(List<String> courseTimeList) throws AllException {
        return merge(translateFromStrToBitList(courseTimeList));
    }

    /**
     * @Description: 时间转换函数: str -> setOf (bit)
     * @Param: [courseTimeList]
     * @Return: java.util.List<java.lang.String>
     * @Author: SoCMo
     * @Date: 2021/5/18
     */
    public static List<String> translateFromStrToBitList(List<String> courseTimeList) throws AllException {
        if (ObjectUtils.isEmpty(courseTimeList) || courseTimeList.size() == 0) {
            return null;
        }
        List<String> timeList = new ArrayList<>();

        for (String courseTime : courseTimeList) {
            StringBuffer sb = new StringBuffer();
            // 形如: 周二:3-5
            String week = courseTime.substring(0, courseTime.indexOf(":"));
            Integer i = WeekEnum.getI(week);
            Integer start = Integer.parseInt(
                    courseTime.substring(
                            courseTime.indexOf(":") + 1,
                            courseTime.indexOf("-"))
            ) - 1;
            Integer end = Integer.parseInt(
                    courseTime.substring(courseTime.indexOf("-") + 1)
            ) - 1;
            for (int k = 0; k < 7; ++k) {
                if (k == i) {
                    for (int m = 0; m < start; ++m) {
                        sb.append("0");
                    }
                    for (int m = start; m <= end; ++m) {
                        sb.append("1");
                    }
                    for (int m = end + 1; m < 13; ++m) {
                        sb.append("0");
                    }
                } else {
                    for (int m = 0; m < 13; ++m) {
                        sb.append("0");
                    }
                }
            }
            timeList.add(sb.toString());
        }
        return timeList;
    }

    /*
     * @Description: 合并多个时间，即多个91位比特合并
     * @Param: [timeList]
     * @return: java.lang.String
     * @Author: pongshy
     * @Date: 2021/5/14
     * @Version: V1.0
     **/
    public static String merge(List<String> timeList) throws AllException {
        if (ObjectUtils.isEmpty(timeList) || timeList.size() == 0) {
            return null;
        }
        StringBuffer res = new StringBuffer();
        StringBuffer sb = new StringBuffer(timeList.get(0));
        for (int i = 1; i < timeList.size(); ++i) {
            String tmp = timeList.get(i);
            for (int j = 0; j < tmp.length(); ++j) {
                if (sb.charAt(j) == '1' && tmp.charAt(j) == '1') {
                    throw new AllException(EmAllException.DATABASE_ERROR, "存在重课");
                }

                if (sb.charAt(j) == '1' || tmp.charAt(j) == '1') {
                    res.append("1");
                } else {
                    res.append("0");
                }
            }
            sb = new StringBuffer(res.toString());
            res.delete(0, res.length());
        }
        res = sb;
        return res.toString();
    }

    /**
     * @program: CourseTool
     * @Description: 选课时间冲突检测
     * @Author: SoCMo
     * @Date: 2021/5/15
     */
    public static boolean conflictCheck(List<String> timeList, String target) {
        List<String> tempList = new ArrayList<>(timeList);
        tempList.add(target);
        try {
            String consume = merge(tempList);
            return true;
        } catch (AllException ex) {
            return false;
        }
    }

    /*
     * @Description: 将bit转换成String类型时间
     * @Param: [bits]
     * @return: java.util.List<java.lang.String>
     * @Author: pongshy
     * @Date: 2021/5/14
     * @Version: V1.0
     **/
    public static List<String> translateFromBitToStr(String bits) {
        if (bits.isEmpty()) {
            return null;
        }
        List<String> res = new ArrayList<>();

        for (int i = 0; i < 7; ++i) {
            // 每一天的情况
            String weekBits = bits.substring(i * 13, i * 13 + 13);
            for (int j = 0; j < 13; ++j) {
                if (weekBits.charAt(j) == '1') {
                    int start = j++;
                    int end = start;
                    while (j < 13 && weekBits.charAt(j) == '1') {
                        end = j++;
                    }
                    String week = WeekEnum.getWeek(i + 1);
                    StringBuffer sb = new StringBuffer();
                    sb.append(week);
                    sb.append(":");
                    sb.append(start + 1);
                    sb.append("-");
                    sb.append(end + 1);
                    res.add(sb.toString());
                }
            }

        }
        return res;
    }

    public static Double gradeToPoint(Double grade) {
        if (ObjectUtils.isEmpty(grade)) {
            return null;
        }
        if (grade < 0 || grade > 100) {
            return null;
        }
        Double res = 0.0;
        if (grade >= 90) {
            res = 4.0;
        } else if (grade >= 85) {
            res = 3.7;
        } else if (grade >= 82) {
            res = 3.3;
        } else if (grade >= 78) {
            res = 3.0;
        } else if (grade >= 75) {
            res = 2.7;
        } else if (grade >= 72) {
            res = 2.3;
        } else if (grade >= 69) {
            res = 2.0;
        } else if (grade >= 65) {
            res = 1.5;
        } else if (grade >= 60) {
            res = 1.0;
        } else {
            res = 0.0;
        }
        return res;
    }


}
