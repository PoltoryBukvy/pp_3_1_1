package com.example.pp311.controller;

import com.example.pp311.model.User;
import com.example.pp311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public String list(Model model) {
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		return "list";
	}

	@GetMapping("/user")
	public String form(Model model) {
		model.addAttribute("user", new User());
		return "form";
	}

	@PostMapping("/user")
	public String save(@ModelAttribute User user, Model model) {
		model.addAttribute("user", userService.save(user));
		return "form";
	}

	@GetMapping(value = "/user/{id}")
	public String show(@PathVariable(value = "id") long id, Model model) {
		Optional<User> user = userService.find(id);
		if (user.isEmpty()) {
			return "404";
		}
		model.addAttribute("user", user.get());
		return "form";
	}

	@PostMapping("/user/{id}")
	public String edit(@ModelAttribute User user, Model model) {
		model.addAttribute("user", userService.save(user));
		return "form";
	}

	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") long id, Model model) {
		Optional<User> user = userService.find(id);
		if (user.isEmpty()) {
			return "404";
		}
		userService.delete(user.get());
		return list(model);
	}
}
