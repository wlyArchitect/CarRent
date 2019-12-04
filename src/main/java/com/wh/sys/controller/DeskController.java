package com.wh.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工作台的控制器
 * 右侧界面展示数据
 * @author 万浩
 * @data 2019/11/16 18:52
 * @description
 */
@RequestMapping("/desk")
@Controller
public class DeskController {
    /**
     * 跳转到工作台界面
     */
    @RequestMapping("/toDeskManager")
    public String toDeskManager(){
        return "system/main/deskManager";
    }
}
