package com.test.demo.services.Impl;

import com.test.demo.dao.BrowseRecordsDao;
import com.test.demo.dao.NewsDao;
import com.test.demo.dao.TagDao;
import com.test.demo.dao.UserDao;
import com.test.demo.dto.InterestDTO;
import com.test.demo.entity.BrowseRecordsEntity;
import com.test.demo.entity.NewsEntity;
import com.test.demo.enumm.RedisKeyEnum;
import com.test.demo.services.NewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsServices {
//定时任务

	@Resource
	private RedisTemplate<String,Object> redisTemplate;

	@Qualifier("newsEntityRedisTemplate")
	@Resource
	private RedisTemplate<String,NewsEntity> newsEntityRedisTemplate;

	@Resource
	private BrowseRecordsDao browseRecordsDao;

	@Resource
	private NewsDao newsDao;

	@Resource
	private TagDao tagDao;

	@Resource
	private UserDao userDao;

	@Override
	public List<NewsEntity> newsList(Long userId) {


		//判断redis是否有用户兴趣集
		InterestDTO interestDTO = getInterestDTO(userId);
		//使用兴趣集获取资讯集合
		List<NewsEntity> result = new ArrayList<>();
		for (Map.Entry<Long, Integer> longIntegerEntry : interestDTO.getIntegerHashMap().entrySet()) {
			ArrayList<Integer> interregional = getInterregional(longIntegerEntry.getValue());
			if (interregional != null) {
				List<NewsEntity> range = newsEntityRedisTemplate.opsForList().range(RedisKeyEnum.NEWS_TAG.getName() + longIntegerEntry.getKey()
						, interregional.get(0), interregional.get(1));
				if (range!= null){
					result.addAll(range);
				}
			}
		}
		final List<Long> longs = interestDTO.getBrowseList();
		//使用结果集中去除掉资讯集
		result = result.stream().filter(e-> !longs.contains(e.getId())).collect(Collectors.toList());
		Collections.shuffle(result);
		return result;
	}


	private InterestDTO getInterestDTO(Long userId) {
		InterestDTO interestDTO = (InterestDTO)redisTemplate.opsForValue().get(RedisKeyEnum.USER_ID.getName() + userId);
		if (interestDTO == null){
			//用户兴趣集不存在，则依据用户浏览记录生成兴趣集对象
			if (browseRecordsDao.selectOneByUserId(userId) == null){
				//用户无浏览记录 使用默认兴趣集对象
				interestDTO = InterestDTO.getDefaultInstance(tagDao.selectId(), userId);
			}else {
				interestDTO = InterestDTO.of(browseRecordsDao.selectThirtyDaysTop100ByUserId(userId),userId,
						browseRecordsDao.selectBrowseNewsIdsByUserId(userId));
			}
			redisTemplate.opsForValue().set(RedisKeyEnum.USER_ID.getName() + userId, interestDTO);
		}
		return interestDTO;
	}

	/**
	 * 根据一个0-100的数 获取一个0-100的随机区间。
	 * 此区间随机起点，终点最大值100，起点最小值0，长度为 @param integer
	 * @param integer 0-100的数
	 * @return 随机起点区间
	 */
	private ArrayList<Integer> getInterregional(Integer integer){

		ArrayList<Integer> list = new ArrayList<>();
		if (integer == null || integer > 100 || integer < 0){
			return null;
		}

		Integer start =(int)Math.floor(Math.random() * (100 - integer) + 0.5);
		Integer end = start + integer;

		list.add(start);
		list.add(end);
		return list;
	}

	@Override
	public NewsEntity browseNews(Long userId,Long newsId) {

		NewsEntity newsEntity = newsDao.selectById(newsId);
		if (newsEntity == null){
			throw new RuntimeException("资讯不存在！");
		}
		//添加浏览记录
		browseRecordsDao.insert(new BrowseRecordsEntity(newsEntity,userId));
		//更新Redis中该用户的兴趣集
		InterestDTO interestDTO = InterestDTO.of(browseRecordsDao.selectThirtyDaysTop100ByUserId(userId),userId,
				browseRecordsDao.selectBrowseNewsIdsByUserId(userId));

		redisTemplate.delete(RedisKeyEnum.USER_ID.getName() + userId);
		redisTemplate.opsForValue().set(RedisKeyEnum.USER_ID.getName() + userId,interestDTO);
		return newsEntity;
	}
}
