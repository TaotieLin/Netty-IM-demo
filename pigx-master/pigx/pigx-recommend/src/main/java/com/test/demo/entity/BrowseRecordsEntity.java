package com.test.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("browse_records")
public class BrowseRecordsEntity extends BaseEntity {

	@TableId(type = IdType.AUTO)
	private Long id;

	private Long userId;

	private Long newsId;

	private Long tagId;

	public BrowseRecordsEntity(NewsEntity newsEntity, Long userId) {
		this.newsId = newsEntity.getId();
		this.tagId = newsEntity.getTagId();
		this.userId = userId;
		this.createDate = new Date();
	}

	public BrowseRecordsEntity() {

	}
}
