package com.test.demo.services;


import java.util.List;

public interface NewsServices {

	List<?> newsList(Long userId);

	Object browseNews(Long userId,Long newsId);

}
