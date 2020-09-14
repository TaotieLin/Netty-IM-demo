package com.test.demo.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.demo.dto.TagIdNumber;
import com.test.demo.entity.BrowseRecordsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lkd
 * @date 2020/9/11
 */
@Mapper
public interface BrowseRecordsDao extends BaseMapper<BrowseRecordsEntity> {



	@Select("select tag_id,count(id) number from browse_records where user_id = #{userId} GROUP BY tag_id order by create_date limit 0,100 ")
	List<TagIdNumber> selectThirtyDaysTop100ByUserId(@Param("userId") Long userId);

	@Select("select id from browse_records where user_id  = #{userId} limit 0,1")
	Long selectOneByUserId(@Param("userId") Long userId);

	@Select("select news_id from browse_records where user_id = #{userId}")
	List<Long> selectBrowseNewsIdsByUserId(@Param("userId") Long userId);







}
