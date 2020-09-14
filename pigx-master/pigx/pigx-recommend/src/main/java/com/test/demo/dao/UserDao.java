package com.test.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lkd
 * @date 2020/9/11
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
}
