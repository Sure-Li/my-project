package com.atguigu.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest {
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(Slf4jTest.class);
		log.debug("debug消息id={}，name={}",1,"zhangsan");
		log.info("这是一个普通的消息");
		log.warn("这是一个警告");
		log.error("错误");
	}
}
