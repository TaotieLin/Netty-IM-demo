package com.test.demo.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.demo.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author MyPC
 */
@Mapper
public interface NewsDao extends BaseMapper<NewsEntity> {

	@Select("select * from news where tag_id = #{id}")
    List<NewsEntity> selectGroudByTag(Long id);

}
