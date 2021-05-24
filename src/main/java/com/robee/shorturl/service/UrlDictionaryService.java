package com.robee.shorturl.service;

import java.util.List;

import com.robee.shorturl.domain.ShortUrl;

public interface UrlDictionaryService {
	
	public List<ShortUrl> getUrls();
	
	public ShortUrl shortenUrl(String longUrl);
	
	public ShortUrl getLongUrl(String shortCode);
	
	public Boolean validateUrl(String longUrl);
}
