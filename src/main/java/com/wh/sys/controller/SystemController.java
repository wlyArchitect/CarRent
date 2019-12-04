package com.wh.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器:作用 路由
 * 系统管理的
 * @author 万浩
 * @data 2019/11/17 10:20
 * @description
 */
@RequestMapping("/sys")
@Controller
public class SystemController {
    /**
     * 跳转菜单管理
     */
    @RequestMapping("/toMenuManager")
    public String toMenuManager(){
        return "system/menu/menuManager";
    }
    /**
     * 跳转菜单管理左边的的菜单树页面
     */
    @RequestMapping("/toMenuLeft")
    public String toMenuLeft() {
        return "system/menu/menuLeft";
    }
    /**
     * 跳转菜单管理右边的菜单列表
     */
    @RequestMapping("/toMenuRight")
    public String toMenuRight() {
        return "system/menu/menuRight";
    }
    /**
     * 跳转角色管理页面
     */
    @RequestMapping("/toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }
    /**
     * 跳转到用户管理界面
     */
    @RequestMapping("/toUserManager")
    public String toUserManager(){
        return "system/user/userManager";
    }
    /**
     * 跳转到日志管理界面
     */
    @RequestMapping("/toLogInfoManager")
    public String toLogInfoManager(){
        return "system/logInfo/logInfoManager";
    }
    /**
     * 跳转到公告管理的界面
     */
    @RequestMapping("/toNewsManager")
    public String toNewsManager() {
        return "system/news/newsManager";
    }

}
