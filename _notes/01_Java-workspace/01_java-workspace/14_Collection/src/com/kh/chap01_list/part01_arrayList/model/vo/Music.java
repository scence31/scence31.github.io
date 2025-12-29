package com.kh.chap01_list.part01_arrayList.model.vo;

public class Music {
	
	// 필드부
	private String title;
	private String artist;
	
	// 생성자부
	public Music() {}

	public Music(String title, String artist) {
		super();
		this.title = title;
		this.artist = artist;
	}
	
	// 메소드부
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
