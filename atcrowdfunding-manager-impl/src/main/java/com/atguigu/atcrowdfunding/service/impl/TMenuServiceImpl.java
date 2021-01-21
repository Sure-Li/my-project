package com.atguigu.atcrowdfunding.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.mapper.TMenuMapper;
import com.atguigu.atcrowdfunding.service.TMenuService;

@Service
public class TMenuServiceImpl implements TMenuService {

	Logger log = LoggerFactory.getLogger(TMenuServiceImpl.class);

	@Autowired
	TMenuMapper menuMapper;

	@Override
	public List<TMenu> listMenuAll() {
		List<TMenu> menuList = new ArrayList<TMenu>();// 只存放父菜单 但是将children属性
		List<TMenu> allList = menuMapper.selectByExample(null);
		Map<Integer, TMenu> cache = new HashMap<Integer, TMenu>();
		for (TMenu tMenu : allList) {
			// 判断是否为父菜单
			if (tMenu.getPid()==0) {
				menuList.add(tMenu);
				cache.put(tMenu.getId(), tMenu);
			}
		}
		
		for (TMenu tMenu : allList) {
			if (tMenu.getPid()!= 0) {
				Integer pid = tMenu.getPid();
				TMenu parent = cache.get(pid);
				parent.getChildren().add(tMenu);
			}
		}
		log.error(menuList.toString());
		return menuList;
	}

}
