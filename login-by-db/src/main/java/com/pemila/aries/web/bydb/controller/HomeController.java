package com.pemila.aries.web.bydb.controller;

import com.pemila.aries.web.bydb.entity.UserEntity;
import com.pemila.aries.web.bydb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author pemila
 * @date 2020/1/20 11:25
 **/
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeController {

	private final UserService userService;

	@GetMapping({"/","/index","/home"})
	public String root(){
		return "index";
	}

	@GetMapping("/login")
	public String login(){
		return "login";
	}

	@GetMapping("/register")
	public String register(){
		return "register";
	}

	@PostMapping("/register")
	public String doRegister(UserEntity userEntity){
		userService.insertUser(userEntity);
		return "redirect:register?success";
	}
}
