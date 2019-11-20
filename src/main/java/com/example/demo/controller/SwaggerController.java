package com.example.demo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户信息查询",tags = {"用户信息的controller"})
public class SwaggerController {

    @PostMapping("/test")
    @ApiOperation(value="修改影城退改签配置",notes="输入参数为影城退改签配置的json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "configs", value = "影院退改签规则", required = true, paramType = "body", dataType = "List")
    })
    public String test(){
        return "hello";
    }
}
