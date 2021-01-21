package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.bean.TAdminExample;
import com.atguigu.atcrowdfunding.exception.LoginException;
import com.atguigu.atcrowdfunding.mapper.TAdminMapper;
import com.atguigu.atcrowdfunding.service.TAdminService;
import com.atguigu.atcrowdfunding.util.AppDateUtils;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.github.pagehelper.PageInfo;

@Service
public class TAdminServiceImpl implements TAdminService {
	@Autowired
	TAdminMapper adminMapper;

	@Override
	public TAdmin getTAdminByLogin(Map<String, Object> paramMap) {

		// 1密码加密

		// 2查询用户
		String loginacct = (String) paramMap.get("loginacct");
		String userpswd = (String) paramMap.get("userpswd");
		// 3判断用户是否为空
		TAdminExample example = new TAdminExample();
		example.createCriteria().andLoginacctEqualTo(loginacct);
		List<TAdmin> list = adminMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			throw new LoginException(Const.LOGIN_LOGINACCT_ERROR);
		}
		TAdmin admin = list.get(0);
		// 4判断密码是否一致
		if (!admin.getUserpswd().equals(MD5Util.digest(userpswd))) {
			throw new LoginException(Const.LOGIN_USERPSWD_ERROR);
		}
		// 5返回结果
		return admin;
	}

	@Override
	public PageInfo<TAdmin> listAdminPage(Map<String, Object> paramMap) {
		TAdminExample example = new TAdminExample();
		example.setOrderByClause("createtime");
		List<TAdmin> list = adminMapper.selectByExample(example);
		PageInfo<TAdmin> page = new PageInfo<TAdmin>(list, 5);
		return page;
	}

	@Override
	public TAdmin getTAdminById(Integer id) {
		return adminMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer saveAdmin(TAdmin admin) {
		admin.setUserpswd(MD5Util.digest(Const.DEFAULT_USERPSWD));
		admin.setCreatetime(AppDateUtils.getFormatTime());
		return adminMapper.insertSelective(admin);
	}

	@Override
	public Integer updateAdmin(TAdmin admin) {
		return adminMapper.updateByPrimaryKeySelective(admin);
	}

	@Override
	public Integer deleteAdmin(Integer id) {
		return adminMapper.deleteByPrimaryKey(id);
	}
}
