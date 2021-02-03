package com.atguigu.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.service.TMenuService;

@Controller
@RequestMapping("/menu")
public class TmenuController {
	
	@Autowired
	TMenuService menuService;
	
	@RequestMapping("/index")
	public String index() {
		return "menu/index";
	}
	@ResponseBody
	@RequestMapping("/loadTree")
	public List<TMenu> loadTree() {
		List<TMenu> result = menuService.listMenuAllTree();
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public String doAdd(TMenu menu) {
		System.err.println(menu);
		return "ok";
	}
	
}
