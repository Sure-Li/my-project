package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.service.TAdminService;
import com.atguigu.atcrowdfunding.util.Const;
import com.sun.tools.internal.ws.processor.model.Model;

// 负责转发
@Controller
public class DispatherController {
	@Autowired
	TAdminService adminService;
	
//	日志
	Logger log = LoggerFactory.getLogger(DispatherController.class);
	
	@RequestMapping("/index")
	public String index() {
		log.debug("跳转到系统主页面。。。");
		return "index";
	}
	@RequestMapping("/login")
	public String login() {
		log.debug("跳转到登录主页面。。。"); 
		return "login";
	}
	@RequestMapping("/doLogin")
	public String doLogin(String loginacct,String userpswd,HttpSession session,org.springframework.ui.Model model) {
		log.debug("开始登陆。。。"); 
		log.debug("loginacct={},userpswd={}",loginacct,userpswd); 
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginacct",loginacct);
		paramMap.put("userpswd",userpswd);
		try {
			TAdmin admin =  adminService.getTAdminByLogin(paramMap);
			session.setAttribute(Const.LOGIN_ADMIN, admin);
			log.debug("登陆成功");
			return "main";
		} catch (Exception e) {
			log.debug("登陆失败={}。。。。"+e.getMessage());
			model.addAttribute("message", e.getMessage());
			return "login";
		}
		
	}
}
