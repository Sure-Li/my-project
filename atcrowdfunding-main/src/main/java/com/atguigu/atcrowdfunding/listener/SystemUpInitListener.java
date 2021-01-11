package com.atguigu.atcrowdfunding.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atguigu.atcrowdfunding.util.Const;
/*
 *  监听application对象的创建与销毁
 *  application会在跟随服务器创建与取消
 * */
public class SystemUpInitListener implements ServletContextListener {

	Logger Logger = LoggerFactory.getLogger(SystemUpInitListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		String contextPath = application.getContextPath();
		Logger.debug("当前路径为："+contextPath);
		application.setAttribute(Const.PATH, contextPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Logger.debug("当前应用已经销毁");
	}

}
