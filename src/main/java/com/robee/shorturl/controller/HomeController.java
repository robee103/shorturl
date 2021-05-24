package com.robee.shorturl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class HomeController {
	
	@GetMapping({"/"})
    public String redirectToRoot() {
        return "redirect:/";
    }
}
