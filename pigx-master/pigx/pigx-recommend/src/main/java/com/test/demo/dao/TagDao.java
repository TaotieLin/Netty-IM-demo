package com.test.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.demo.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lkd
 * @date 2020/9/11
 */
@Mapper
public interface TagDao extends BaseMapper<TagEntity> {

	@Select("select id from tag")
    List<Long> selectId();
}
