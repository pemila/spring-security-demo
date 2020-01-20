package com.pemila.aries.web.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author pemila
 * @date 2020/1/19 16:53
 **/
@Controller
public class HomeController {

	@GetMapping({"/","/index","/home"})
	public String index(){
		return "index";
	}

	@GetMapping("/login")
	public String login(){
		return "login";
	}

}
