package com.robee.shorturl.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robee.shorturl.domain.ShortUrl;
import com.robee.shorturl.repo.UrlDictionaryRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api")
@Slf4j
public class UrlDictionaryController {
	
	@Autowired
	private UrlDictionaryRepo urlDictionaryRepo;
	
	/**
     * @param fullUrl Takes an object of FullUrl supplied in the request body
     * @return An object of ShortUrl serialized as JSON in the response
     */
	@GetMapping("/urls")
	public ResponseEntity<List<ShortUrl>> getUrls(){
		List<ShortUrl> shortUrls = new ArrayList<>();; 
		urlDictionaryRepo.findAll().forEach(shortUrls::add);
		return new ResponseEntity<List<ShortUrl>>(shortUrls, HttpStatus.OK);
	}
	
	@PostMapping("/shorten")
	public ResponseEntity<List<ShortUrl>> shortenUrl(){
		List<ShortUrl> shortUrls = new ArrayList<>();; 
		urlDictionaryRepo.findAll().forEach(shortUrls::add);
		return new ResponseEntity<List<ShortUrl>>(shortUrls, HttpStatus.OK);
	}
	
	@GetMapping("/shorten/{shortCode}")
	public ResponseEntity<List<ShortUrl>> getLongUrl(@PathVariable("shortCode") String shortCode){
		List<ShortUrl> shortUrls = new ArrayList<>();; 
		urlDictionaryRepo.findAll().forEach(shortUrls::add);
		return new ResponseEntity<List<ShortUrl>>(shortUrls, HttpStatus.OK);
	}
	
}
