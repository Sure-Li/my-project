package com.atguigu.atcrowdfunding.service;

import java.util.Map;

import com.atguigu.atcrowdfunding.bean.TAdmin;

public interface TAdminService {

	TAdmin getTAdminByLogin(Map<String, Object> paramMap);

}
