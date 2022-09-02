package com.abatplus.url_shortener.urlShortenerLogic;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abatplus.url_shortener.Repo.Repo;
import com.abatplus.url_shortener.urlDatabase.UrlData;
import com.abatplus.url_shortener.urlDatabase.UrlDto;
import com.abatplus.url_shortener.error.ErrorMessages;



@Service
public class UrlShortenerLogic {
	
	@Autowired
	private Repo repo;
	
	@Autowired
	public UrlShortenerLogic(Repo repo) {
		this.setRepo(repo);
	}

	public Repo getRepo() {
		return repo;
	}

	public void setRepo(Repo repo) {
		this.repo = repo;
	}
	
	public ResponseEntity<?> create(UrlDto urlDto) {
		
		UrlData urlData = new UrlData();
		char urlId;  
		
		if(urlDto.getUrl() == null) {
			
			System.out.println(urlDto.getUrl());
			ErrorMessages errorMessage = new ErrorMessages(new Date(), "No Url has been set!", 400, "Bad Request");
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
			
		} else urlData.setOriginUrl(urlDto.getUrl());
		
		
		if(urlDto.getUrlId() == '\0') {

			urlId = randomChar();
			
		} else urlId = urlDto.getUrlId(); 
		
		boolean check = repo.existsByUrlId(urlId);
		
		if(check == true) {
			
			ErrorMessages errorMessage = new ErrorMessages(new Date(), "Url Id already exists", 400, "Bad Request");
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
			
		} else {
			
			urlData.setUrlId(urlId);
			urlData.setTtl(urlDto.getTtl());
			UrlData createdTable = repo.save(urlData);
			
			return new ResponseEntity<>(createdTable, HttpStatus.CREATED);
		}		
	}
	
	public char randomChar() {
		
		String randomizedString = RandomStringUtils.randomAlphanumeric(1);
        char randomizedCharacter = randomizedString.charAt(0);
        return randomizedCharacter;
		
	}
	
	public ResponseEntity<?> redirect(char id) throws URISyntaxException {
		
		try {			
			UrlData list =  repo.findAllByUrlId(id);
			String orgUrl = list.getOriginUrl();
			URI uri = new URI(orgUrl);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uri);
			
			return new ResponseEntity<>(orgUrl ,httpHeaders, HttpStatus.MOVED_PERMANENTLY);
			
		}catch(Exception e) {
			
			ErrorMessages errorMessage = new ErrorMessages(new Date(), "Url Id does not exist!", 400, "Bad Request");
			return new ResponseEntity<>(errorMessage, HttpStatus.MOVED_PERMANENTLY);

		}			
	}
	
	public List<UrlData> getAllUrls(){
		
		return repo.findAll();
	}
	
	public ResponseEntity<?> delete(char urlId) {
		
		boolean checkDelete = repo.existsByUrlId(urlId);
		if(checkDelete == false) {
			ErrorMessages errorMessage = new ErrorMessages(new Date(), "Url Id does not exists", 400, "Bad Request");
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		} else {
			repo.deleteByUrlId(urlId);
			return new ResponseEntity<>("Successfully deleted!", HttpStatus.OK);
		}
		
	}
	
	public ResponseEntity<?> edit(UrlData urlData){
		
		Boolean checkEdit = repo.existsById(urlData.getId());
		if(checkEdit == false) {
			
			ErrorMessages errorMessage = new ErrorMessages(new Date(), "Id does not exists", 400, "Bad Request");
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(repo.save(urlData), HttpStatus.OK);
		
	}
	
	public void checkTtl() {
		
		List<UrlData> urlList = repo.findAll();
		
		for(UrlData row : urlList) {
			
			try {
				LocalDateTime localTimeData = row.getTtl();
				LocalDateTime localTime = LocalDateTime.now(); 
				int compare = localTime.compareTo(localTimeData);
				
				if(compare > 0) {
					repo.deleteById(row.getId());
				}	
			}catch(NullPointerException  e) {
				
			}			
		}
	}
	
	public List<String> returnAll(){
		
		List<UrlData> returnList = repo.findAll();
		List urlList = new ArrayList();
			
		for(UrlData url : returnList) {
			
			urlList.add(url.getOriginUrl());
		}
		
		return urlList;
	}	
}
