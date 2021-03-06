package com.shu.course_backend.controller;

import com.shu.course_backend.model.Result;
import com.shu.course_backend.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * program: ConfigController
 * description: 参数控制层
 * author: SoCMo
 * create: 2021/4/27 16:10
 */
@RestController
@RequestMapping("/config")
@Api(tags = "系统配置资源")
@Slf4j
@RequiredArgsConstructor
public class ConfigController {
    private final ConfigService configService;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/semester")
    @ApiOperation(value = "更新当前学期")
    @ApiImplicitParam(name = "nowSemester", value = "学期,ex:2021(0-3),分别表示春夏秋冬季", required = true, dataType = "string")
    public Result updateSemester(@RequestBody @ApiIgnore Map<String, Object> paramsMap) {
        return configService.updateSemester(paramsMap.get("nowSemester").toString());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/electionState")
    @ApiOperation(value = "更新当前选课状态")
    @ApiImplicitParam(name = "state", value = "true或false", required = true, dataType = "int")
    public Result updateElectionStat(@RequestBody @ApiIgnore Map<String, Object> paramsMap) {
        return configService.updateElectionState(paramsMap.get("state").toString());
    }
}
