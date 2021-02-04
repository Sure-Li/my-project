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
		String result = "false";
		if (menuService.doAdd(menu) > 0) {
			result = "ok";
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/getMenuById")
	public TMenu getMenuById(Integer id) {
		return menuService.getMenuById(id);
	}
	
	@ResponseBody
	@RequestMapping("/doUpdate")
	public String doUpdate(TMenu menu) {
		String result = "false";
		if (menuService.doUpdate(menu) > 0) {
			result = "ok";
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/doDelete")
	public Integer doDelete(Integer id) {
		return menuService.doDelete(id);
	}
}
