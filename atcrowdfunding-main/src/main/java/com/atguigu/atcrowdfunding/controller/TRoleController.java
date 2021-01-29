package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class TRoleController {
	@Autowired
	TRoleService roleService;

	@RequestMapping("/role/index")
	public String index() {
		return "role/index";
	}

	@ResponseBody
	@RequestMapping("/role/loadData")
	public PageInfo<TRole> loadData(
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "2") Integer pageSize,
			@RequestParam(value = "condition", required = false, defaultValue = "") String condition) {
		PageHelper.startPage(pageNum, pageSize);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("condition", condition);
		PageInfo<TRole> page = roleService.listRolePage(paramMap);
		return page;
	}
}
