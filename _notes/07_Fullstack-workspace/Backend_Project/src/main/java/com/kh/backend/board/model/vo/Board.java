package com.kh.backend.board.model.vo;

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
@Alias("board")
public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String originName;
	private String changeName;
	private int count;
	private Date createDate;
	private String status;

}
