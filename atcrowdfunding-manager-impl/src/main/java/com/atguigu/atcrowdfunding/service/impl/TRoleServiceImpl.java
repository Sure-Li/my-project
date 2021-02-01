package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.bean.TRoleExample;
import com.atguigu.atcrowdfunding.bean.TRoleExample.Criteria;
import com.atguigu.atcrowdfunding.mapper.TRoleMapper;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.github.pagehelper.PageInfo;

@Service
public class TRoleServiceImpl implements TRoleService {
	@Autowired
	TRoleMapper roleMapper;

	@Override
	public PageInfo<TRole> listRolePage(Map<String, Object> paramMap) {
		String condition = (String) paramMap.get("condition");
		List<TRole> list = null;
		if (StringUtils.isEmpty(condition)) {
			list = roleMapper.selectByExample(null);
		} else {
			TRoleExample example = new TRoleExample();
			example.createCriteria().andNameLike("%" + condition + "%");
			list = roleMapper.selectByExample(example);
		}

		PageInfo<TRole> page = new PageInfo<TRole>(list, 5);
		return page;
	}

	@Override
	public int doAdd(TRole role) {
		return roleMapper.insertSelective(role);
	}

	@Override
	public TRole getRoleById(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int doUpdate(TRole role) {
		TRoleExample example = new TRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(role.getId());
		return roleMapper.updateByExampleSelective(role, example);
	}

	@Override
	public Integer doDelete(Integer id) {
		return roleMapper.deleteByPrimaryKey(id);
	}
}
