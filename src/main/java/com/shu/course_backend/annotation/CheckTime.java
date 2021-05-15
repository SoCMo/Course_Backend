package com.shu.course_backend.annotation;

import com.auth0.jwt.interfaces.Payload;

import javax.validation.Constraint;
import java.lang.annotation.*;

/*
 * @Description: 注解——检测List<String>中的字符串格式
 * @Version: 1.0
 * @Author: pongshy
 * @Date: 2021/5/15 15:30
 */
@Documented
@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeValidator.class)
public @interface CheckTime {


    String message() default "{time.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
