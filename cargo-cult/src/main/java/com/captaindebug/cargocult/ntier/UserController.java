package com.captaindebug.cargocult.ntier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.captaindebug.cargocult.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/find1")
	public String findUser(@RequestParam("user") String name, Model model) {

		User user = userService.findUser(name);
		model.addAttribute("user", user);
		return "user";
	}
}
