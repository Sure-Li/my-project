package com.atguigu.atcrowdfunding.exception;

// 为什么继承RuntimeException 而不是Exception Spring声明事务默认的只对运行时异常回滚
public class LoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public LoginException() {}
	public LoginException(String message) {
		super(message); 
	}
}
