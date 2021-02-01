package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	@ResponseBody
	@RequestMapping("/role/doAdd")
	public String doAdd(TRole role) {
		String result = "failure";
		if(roleService.doAdd(role)>0) {
			result="ok";
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/role/doUpdate")
	public String doUpdate(TRole role) {
		String result = "failure";
		if(roleService.doUpdate(role)>0) {
			result="ok";
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/role/getRoleById")
	public TRole getRoleById(Integer id) {
		return roleService.getRoleById(id);
	} 
	@ResponseBody
	@RequestMapping("/role/doDelete")
	public Integer doDelete(Integer id) {
		return roleService.doDelete(id);
	} 
	
}
