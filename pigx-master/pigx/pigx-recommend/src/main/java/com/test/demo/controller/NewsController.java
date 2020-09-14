package com.test.demo.controller;

import com.alibaba.nacos.config.server.model.RestResult;
import com.baomidou.mybatisplus.extension.api.R;
import com.test.demo.services.NewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news/")
public class NewsController {

	@Autowired
	private NewsServices newsServices;

	@RequestMapping("newsList")
	public R<?> newsList(@RequestHeader Long userId){
		return R.ok(newsServices.newsList(userId));
	}

	@RequestMapping("browseNews")
	public R<?> browseNews(@RequestHeader Long userId,Long newsId){
		return R.ok(newsServices.browseNews(userId,newsId));
	}



}
