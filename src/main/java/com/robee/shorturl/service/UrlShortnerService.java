package com.robee.shorturl.service;

public interface UrlShortnerService {
	public String shortenUrl(String longUrl);
	
	public String validateUrl(String longUrl);
}
