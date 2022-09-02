package com.abatplus.url_shortener.urlDatabase;

import java.time.LocalDateTime;

public class UrlDto {
	
	public String url;
	public char urlId;
	public LocalDateTime ttl;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public char getUrlId() {
		return urlId;
	}
	public void setUrlId(char urlId) {
		this.urlId = urlId;
	}
	public LocalDateTime getTtl() {
		return ttl;
	}
	public void setTtl(LocalDateTime ttl) {
		this.ttl = ttl;
	}
	public UrlDto(String url, char urlId, LocalDateTime ttl) {
		super();
		this.url = url;
		this.urlId = urlId;
		this.ttl = ttl;
	}
	
	public UrlDto() {
		
	}
	@Override
	public String toString() {
		return "UrlDto [url=" + url + ", urlId=" + urlId + ", ttl=" + ttl + "]";
	}
	
	

}
