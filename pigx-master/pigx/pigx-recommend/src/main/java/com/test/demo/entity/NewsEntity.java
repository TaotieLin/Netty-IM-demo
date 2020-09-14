package com.test.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("news")
public class NewsEntity extends BaseEntity {

	@TableId(type = IdType.AUTO)
	private Long id;

	private String title;

	private String photo;

	private Long tagId;

	private String content;

}
