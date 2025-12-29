package com.kh.backend.notice.model.vo;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Alias("notice")
public class Notice {
	
	private int noticeNo;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeContent;
	private Date createDate;
	private String status;

}
