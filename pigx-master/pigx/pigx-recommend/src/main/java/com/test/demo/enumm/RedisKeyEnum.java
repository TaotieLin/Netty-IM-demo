package com.test.demo.enumm;

import lombok.Data;

/**
 * @author lkd
 * @date 2020/9/11
 */

public enum RedisKeyEnum {
	/**
	 * 新闻标签
	 */
	NEWS_TAG("NEWS:TAG:"),
	USER_ID("USER:ID:");

	RedisKeyEnum(String name){
		this.name =name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
