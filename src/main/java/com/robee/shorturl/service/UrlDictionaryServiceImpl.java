package com.robee.shorturl.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.google.common.hash.Hashing;
import com.robee.shorturl.domain.ShortUrl;
import com.robee.shorturl.repo.UrlDictionaryRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UrlDictionaryServiceImpl implements UrlDictionaryService {
	
	@Autowired
	private UrlValidator urlValidator;
	
	@Autowired
	private UrlDictionaryRepo repo;
	
	@Override
	public List<ShortUrl> getUrls() {
		List<ShortUrl> urls = new ArrayList<>();
		repo.findAll().forEach(urls::add);
		return urls;
	}

	@Override
	public ShortUrl shortenUrl(String longUrl) {
		boolean isValid = validateUrl(longUrl);
		if(isValid) {
			String shortCode = Hashing.murmur3_32()
					.hashString(longUrl, Charset.defaultCharset()).toString();
			ShortUrl shortUrl = ShortUrl.builder()
					.longUrl(longUrl)
					.shortCode(shortCode)
					.visitCount(0L)
					.build();
			return repo.save(shortUrl);
		}
		else {
			log.error("Invalid URL Format: {}", longUrl);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid URL Format");	
		}
	}
	
	@Override
	public ShortUrl getLongUrl(String shortCode) {
		ShortUrl shortUrl = null;
		Optional<ShortUrl> optShortUrl = Optional.of(repo.findTopByShortCode(shortCode));
		shortUrl = optShortUrl.orElseThrow(null);
		
		ShortUrl updUrl = repo.findById(shortUrl.getId()).orElse(shortUrl);
		updUrl.setVisitCount(updUrl.getVisitCount()+1);
		
		return repo.save(updUrl);
	}

	@Override
	public Boolean validateUrl(String longUrl) {
		return Objects.nonNull(longUrl) && urlValidator.isValid(longUrl);
	}

}
