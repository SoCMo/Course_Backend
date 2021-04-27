package com.shu.course_backend.service.Impl;

import com.shu.course_backend.dao.ConstDoMapper;
import com.shu.course_backend.exception.AllException;
import com.shu.course_backend.exception.EmAllException;
import com.shu.course_backend.model.Result;
import com.shu.course_backend.model.entity.ConstDo;
import com.shu.course_backend.service.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * program: ConfigServiceImpl
 * description: 参数实现类
 * author: SoCMo
 * create: 2021/4/27 16:13
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {
    private final ConstDoMapper constDoMapper;

    @Override
    public Result updateSemester(String nowSemester) {
        if(!nowSemester.matches("^\\d{4}[0-3]$")){
            return Result.error(new AllException(EmAllException.BAD_REQUEST, "学期格式错误"));
        }

        ConstDo constDo = new ConstDo();
        constDo.setConfigKey("NOW_SEMESTER");
        constDo.setConfigValue(nowSemester);

        if(constDoMapper.updateByPrimaryKey(constDo) == 1){
            return Result.success(constDo);
        } else return Result.error(new AllException(EmAllException.DATABASE_ERROR));
    }
}
