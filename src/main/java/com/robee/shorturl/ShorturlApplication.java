package com.robee.shorturl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.robee.shorturl.domain.ShortUrl;
import com.robee.shorturl.repo.UrlDictionaryRepo;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ShorturlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShorturlApplication.class, args);
	}
	
	@Bean
	  public CommandLineRunner demo(UrlDictionaryRepo repository) {
	    return (args) -> {
	      // save a few customers
	      repository.save(ShortUrl.builder().longUrl("http://abc.com/api").build());

	      // fetch all customers
	      log.info("Customers found with findAll():");
	      log.info("-------------------------------");
	      for (ShortUrl customer : repository.findAll()) {
	        log.info(customer.toString());
	      }
	      log.info("");

	      // fetch an individual customer by ID
	      ShortUrl customer = repository.findShortUrlByLongUrl("http://abc.com/api").get(0);
	      log.info("Customer found with findShortUrlByLongUrl(1L):");
	      log.info("--------------------------------");
	      log.info(customer.toString());
	      log.info("");

	      log.info("");
	    };
	  }

}
