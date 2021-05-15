package com.shu.course_backend.annotation;

import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @ClassName: TimeValidator
 * @Description:
 * @Author: pongshy
 * @Date: 2021/5/15 15:54
 **/
public class TimeValidator implements ConstraintValidator<CheckTime, List<String>> {


    private String regex = "^[\\u4e00-\\u9fa5]{2}:[0-9]{1,2}-[0-9]{1,2}$";

    @Override
    public void initialize(CheckTime constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<String> stringList, ConstraintValidatorContext constraintValidatorContext) {
        if (ObjectUtils.isEmpty(stringList)) {
            return true;
        }
        Pattern pt = Pattern.compile(regex);
        for (String tmp : stringList) {
            if (!pt.matcher(tmp).matches()) {
                return false;
            }
        }
        return true;
    }
}
