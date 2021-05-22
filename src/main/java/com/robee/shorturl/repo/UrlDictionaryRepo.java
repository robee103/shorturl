package com.robee.shorturl.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.robee.shorturl.domain.ShortUrl;

public interface UrlDictionaryRepo extends CrudRepository<ShortUrl, Long> {
    List<ShortUrl> findShortUrlByLongUrl(String longUrl);
}
