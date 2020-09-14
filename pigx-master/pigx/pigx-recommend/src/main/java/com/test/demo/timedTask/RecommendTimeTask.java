package com.test.demo.timedTask;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.demo.dao.NewsDao;
import com.test.demo.dao.TagDao;
import com.test.demo.entity.NewsEntity;
import com.test.demo.entity.TagEntity;
import com.test.demo.enumm.RedisKeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lkd
 * @date 2020/9/11
 */
@Slf4j
@Component
public class RecommendTimeTask {

	private static final Logger LOG = LoggerFactory.getLogger(RecommendTimeTask.class);

	@Resource
	private NewsDao newsDao;

	@Resource
	private TagDao tagDao;

	@Resource
	private RedisTemplate<String,NewsEntity> redisTemplate;

	/**
	 * 定时将数据库中最新的资讯按类别添加到数据库中每个类别100条过期时间2小时
	 */
	@Scheduled(initialDelay = 0,fixedDelay = 1000*60*60*2L)
	public void autoCacheNews(){

		System.out.println("Redis开始缓存热点资讯A");
		List<TagEntity> tagEntities = tagDao.selectList(new QueryWrapper<>());
		for (TagEntity tagEntity : tagEntities) {
			List<NewsEntity> newsEntities = newsDao.selectGroudByTag(tagEntity.getId());
			if (newsEntities != null && newsEntities.size() != 0) {
				redisTemplate.delete(RedisKeyEnum.NEWS_TAG.getName() + tagEntity.getId());
				redisTemplate.opsForList().leftPushAll(RedisKeyEnum.NEWS_TAG.getName() + tagEntity.getId(), newsEntities);
			}
		}
		System.out.println("Redis热点资讯缓存完毕A");
		log.info("Redis热点资讯缓存完毕");
		LOG.info("Redis!!!!!!!!!!!!!!!!");
	}


}
