package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.service.TAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class TAdminController {
	@Autowired
	private TAdminService adminService;

	@RequestMapping("admin/index")
	public String index(@RequestParam(value="pageNum",required=false,defaultValue="1") Integer pageNum,@RequestParam(value="pageSize",required=false,defaultValue="2") Integer pageSize,Model model){
		PageHelper.startPage(pageNum, pageSize);//线程绑定
		Map<String,Object> paramMap = new HashMap<String, Object>();
		PageInfo<TAdmin> page =  adminService.listAdminPage(paramMap);
		model.addAttribute("page", page);
		return "admin/index";
	}
	@RequestMapping("admin/toupdate")
	public String toUpdate(Integer id,Model model){
		TAdmin admin = adminService.getTAdminById(id);
		model.addAttribute("admin", admin);
		return "admin/update";
	}
	@RequestMapping("admin/doUpdate")
	public String doUpdate(TAdmin admin,Integer pageNum){
		adminService.updateAdmin(admin);
		return "redirect:/admin/index?pageNum="+pageNum;
	}

	
	@RequestMapping("admin/toadd")
	public String toAdd(){
		return "admin/add";
	}
	@RequestMapping("admin/doAdd")
	public String doAdd(TAdmin admin){
		adminService.saveAdmin(admin);
		return "redirect:/admin/index?pageNum="+Integer.MAX_VALUE;
	}
	@RequestMapping("admin/doDelete")
	public String doDelete(Integer pageNum,Integer id){
		adminService.deleteAdmin(id);
		return "redirect:/admin/index?pageNum="+pageNum;
	}
}
	