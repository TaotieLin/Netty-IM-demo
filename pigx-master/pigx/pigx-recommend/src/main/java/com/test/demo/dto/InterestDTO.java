package com.test.demo.dto;

import com.test.demo.entity.BrowseRecordsEntity;
import com.test.demo.entity.NewsEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author lkd
 * @date 2020/9/11
 */
@Data
public class InterestDTO {

	private Long userId;

	private HashMap<Long,Integer> integerHashMap;

	private List<Long> browseList;

	/**
	 * 根据浏览记录生成兴趣集对象
	 */
	public InterestDTO(List<BrowseRecordsEntity> browseRecordsEntities){



	}

	public InterestDTO() {}

	public static InterestDTO getDefaultInstance(List<Long> longs ,Long userId) {
		InterestDTO interestDTO = new InterestDTO();
		interestDTO.userId = userId;
		HashMap<Long,Integer> map = new HashMap<>();
		for (Long aLong : longs) {
			map.put(aLong,(int)Math.floor(100d / longs.size()));
		}
		interestDTO.integerHashMap = map;
		interestDTO.browseList = new ArrayList<>();
		return interestDTO;
	}

	/**
	 * 根据标签对应数量列表，用户id，已阅读资讯id列表生成用户兴趣对象
	 * @param tagIdNumbers 标签对应数量列表
	 * @param userId 用户id
	 * @param browseList 已阅读资讯id列表
	 * @return 兴趣对象
	 */
	public static InterestDTO of(List<TagIdNumber> tagIdNumbers,Long userId,List<Long> browseList) {

		InterestDTO interestDTO = new InterestDTO();
		interestDTO.userId = userId;
		HashMap<Long,Integer> map = new HashMap<>();
		Optional<Integer> reduce = tagIdNumbers.stream().map(TagIdNumber::getNumber).reduce(Integer::sum);
		if (!reduce.isPresent()){
			throw new RuntimeException("兴趣集计算异常！");
		}

		for (TagIdNumber tagIdNumber : tagIdNumbers) {
			double i= 100 * ((double)tagIdNumber.getNumber()/ (double)reduce.get());
			map.put(tagIdNumber.getTagId(),(int)i);
		}
		interestDTO.integerHashMap = map;
		interestDTO.browseList = browseList;
		return interestDTO;
	}

	/**
	 * 向该兴趣集对象新增一条浏览记录
	 * 1、添加过滤文章id
	 * 2、修正兴趣集合权重
	 */
	public void addBrowseFromInterest(NewsEntity newsEntity){

		List<Long> browseList = this.getBrowseList();
		if (!browseList.contains(newsEntity.getId())) {
			browseList.add(newsEntity.getId());
		}
		this.setBrowseList(browseList);

		HashMap<Long, Integer> integerHashMap = this.getIntegerHashMap();
		Integer integer = integerHashMap.get(newsEntity.getTagId());
		if (integer == null){
			integer = 0;
		}
		integerHashMap.put(newsEntity.getTagId(),integer+1);
		this.setIntegerHashMap(integerHashMap);
	}
}
