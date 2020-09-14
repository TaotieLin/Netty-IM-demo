/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.test.demo.exception;

import com.baomidou.mybatisplus.extension.api.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 * 异常处理器
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@RestControllerAdvice
public class RenExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(RenExceptionHandler.class);

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException ex){
		R result = R.failed(ex.getMessage());
		logger.info(ex.getMessage());
		System.out.println(ex.getMessage());
		return result;
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception ex){
		logger.error(ex.getMessage(), ex);
		System.out.println(ex.getMessage());
		ex.printStackTrace();
		return  R.failed(ex.getMessage());
	}
}