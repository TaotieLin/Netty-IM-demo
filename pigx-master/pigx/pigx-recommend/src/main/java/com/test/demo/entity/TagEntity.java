package com.test.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

@Data
@TableName("tag")
public class TagEntity extends BaseEntity{

	@TableId(type = IdType.AUTO)
	private Long id;

	private String name;



}
