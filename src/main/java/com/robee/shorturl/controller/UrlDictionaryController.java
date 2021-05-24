package com.robee.shorturl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.robee.shorturl.domain.ShortUrl;
import com.robee.shorturl.service.UrlDictionaryService;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class UrlDictionaryController {
	
	@Autowired
	private UrlDictionaryService urlDictionaryService;
	
	/**
     * @param fullUrl Takes an object of FullUrl supplied in the request body
     * @return An object of ShortUrl serialized as JSON in the response
     */
	@GetMapping("/api/")
	public ResponseEntity<List<ShortUrl>> getUrls(){
		List<ShortUrl> shortUrls = urlDictionaryService.getUrls();
		return new ResponseEntity<>(shortUrls, HttpStatus.OK);
	}
	
	@PostMapping("/api/shorten")
	public ResponseEntity<ShortUrl> shortenUrl(@RequestBody String longUrl){
		ShortUrl shortUrl = urlDictionaryService.shortenUrl(longUrl);
		return new ResponseEntity<>(shortUrl, HttpStatus.OK);
	}
	
	@GetMapping("/api/shorten/{shortCode}")
	public ResponseEntity<ShortUrl> getLongUrl(@PathVariable("shortCode") String shortCode){
		ShortUrl shortUrl = urlDictionaryService.getLongUrl(shortCode); 
		return new ResponseEntity<>(shortUrl, HttpStatus.OK);
	}
	
}
