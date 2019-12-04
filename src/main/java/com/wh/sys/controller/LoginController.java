package com.wh.sys.controller;

import com.wh.sys.constant.SysConstant;
import com.wh.sys.entity.User;
import com.wh.sys.service.UserService;
import com.wh.sys.utils.WebUtils;
import com.wh.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登陆控制器
 * @author LJH
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userService;
	/**
	 * 跳转到登陆页面
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "system/main/login";
	}
	/**
	 * 登陆方法
	 */
	@RequestMapping("/login")
	public String login(UserVo userVo, Model model) {
		User user=this.userService.login(userVo);
		if(null!=user) {
			//放到session
			WebUtils.getHttpSession().setAttribute("user", user);
			//记录登陆日志 向sys_login_log里面插入数据
			return "system/main/index";
		}else {
			model.addAttribute("error", SysConstant.USER_LOGIN_ERROR);
			return "system/main/login";
		}
	}
}