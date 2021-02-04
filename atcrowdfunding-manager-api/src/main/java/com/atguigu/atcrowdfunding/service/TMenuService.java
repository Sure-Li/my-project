package com.atguigu.atcrowdfunding.service;

import java.util.List;

import com.atguigu.atcrowdfunding.bean.TMenu;

public interface TMenuService {

	List<TMenu> listMenuAll(); //组合父子关系的
	
	List<TMenu> listMenuAllTree();//不用组合父子关系

	int doAdd(TMenu menu);

	TMenu getMenuById(Integer id);

	int doUpdate(TMenu menu);

	Integer doDelete(Integer id);

}
