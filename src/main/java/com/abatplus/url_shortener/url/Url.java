package com.abatplus.url_shortener.url;

public class Url {
	
	private String url;
	private String expTime;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getExpTime() {
		return expTime;
	}
	public void setExpTime(String expTime) {
		this.expTime = expTime;
	}
	
	public Url(String url, String expTime) {
		this.url = url;
		this.expTime = expTime;
	}
	@Override
	public String toString() {
		return "Url [url=" + url + ", expTime=" + expTime + "]";
	}
	
}
