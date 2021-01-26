package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.github.pagehelper.PageInfo;

public interface TAdminService {

	TAdmin getTAdminByLogin(Map<String, Object> paramMap);

	PageInfo<TAdmin> listAdminPage(Map<String, Object> paramMap);

	TAdmin getTAdminById(Integer id);

	Integer saveAdmin(TAdmin admin);

	Integer updateAdmin(TAdmin admin);

	Integer deleteAdmin(Integer id);

	Integer doDeleteBatch(List<Integer> idList);

}
