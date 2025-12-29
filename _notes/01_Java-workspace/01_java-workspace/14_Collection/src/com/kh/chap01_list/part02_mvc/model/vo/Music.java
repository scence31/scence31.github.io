package com.kh.chap01_list.part02_mvc.model.vo;

// 1단계 vo클래스
public class Music {
	
	// 필
	private String title;
	private String artist; 
	// 생
	public Music() {}

	public Music(String title, String artist) {
		super();
		this.title = title;
		this.artist = artist;
	}
	
	// 메
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	
	@Override
	public String toString() {
		return "Music [title=" + title + ", artist=" + artist + "]";
	}
	
	

}
