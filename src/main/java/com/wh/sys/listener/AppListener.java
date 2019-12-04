package com.wh.sys.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 使用@WebListener注解代替在web.xml中的配置
 *
 * @author 万浩
 * @data 2019/11/15 19:19
 * @description
 */
@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("servletContext对象被销毁!");
    }
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //取到ServletContext
        ServletContext context=arg0.getServletContext();
        context.setAttribute("whContextPath", context.getContextPath());
        System.err.println("---------Servlet容器创建成功 wh被放到ServletContext作用域-------");
    }
}
