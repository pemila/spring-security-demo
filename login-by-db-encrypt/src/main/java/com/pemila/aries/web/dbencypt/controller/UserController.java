package com.pemila.aries.web.dbencypt.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * @author pemila
 * @date 2020/1/20 11:28
 **/
@Controller
public class UserController {

	@GetMapping("/user")
	public String user(@AuthenticationPrincipal Principal principal, Model model){
		model.addAttribute("username",principal.getName());
		return "user/user";
	}
}
