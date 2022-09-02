package com.abatplus.url_shortener.urlDatabase;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity 
@Table(name="Url")
public class UrlData {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="OriginalUrl")
	@NonNull
	private String originUrl;
	
	@NonNull
	@Column(name="ShortUrl")
	private char urlId;

	@Column(name="Timetolive")
	private LocalDateTime ttl;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOriginUrl() {
		return originUrl;
	}
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
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
	
	public UrlData() {
		
	}
	
	public UrlData(Long id, String originUrl, char urlId, LocalDateTime ttl) {
		super();
		this.id = id;
		this.originUrl = originUrl;
		this.urlId = urlId;
		this.ttl = ttl;
	}
	@Override
	public String toString() {
		return "UrlData [id=" + id + ", originUrl=" + originUrl + ", urlId=" + urlId + ", ttl=" + ttl + "]";
	}

	


}
