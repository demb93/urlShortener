package com.abatplus.url_shortener.controller;



import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.abatplus.url_shortener.urlDatabase.UrlData;
import com.abatplus.url_shortener.urlDatabase.UrlDto;
import com.abatplus.url_shortener.urlShortenerLogic.UrlShortenerLogic;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@EnableScheduling
public class RedirectController {
	
	@Autowired
	private UrlShortenerLogic urlShortenerLogic;
	
	
	@GetMapping("/urls/{id}")
	public ResponseEntity<?> handleRedirect(@PathVariable char id) throws URISyntaxException {

		 ResponseEntity<?> list = urlShortenerLogic.redirect(id); 
		 
		 return list;
	}
	
	@PostMapping("/")
	public ResponseEntity<?> urlShortener(@Validated @RequestBody UrlDto urlDto) {
		
		ResponseEntity<?> responseEntity = urlShortenerLogic.create(urlDto);
		
		return responseEntity;

	}

	@DeleteMapping("/urls/delete/{id}")
	public ResponseEntity<?> deleteId(@PathVariable char id) throws URISyntaxException {

		 return urlShortenerLogic.delete(id); 

	}
	
	@PutMapping("/urls/edit/{id}")
	public ResponseEntity<?> edit(@PathVariable Long id, @Validated @RequestBody UrlData urlData) throws URISyntaxException {
		
		urlData.setId(id);
		
		return new ResponseEntity<>(urlShortenerLogic.edit(urlData), HttpStatus.OK);

	}
	
	@Scheduled(fixedRate = 10000)
	public void checkTtl() {
		
		urlShortenerLogic.checkTtl();
	}
	
	@GetMapping("/urls")
	public ResponseEntity<?> returnAll() {
		
		return new ResponseEntity<>(urlShortenerLogic.returnAll(), HttpStatus.OK);
	}
}
