package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController 
{
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index()
	{
		return "index";
	}
	
	@GetMapping("/register")
	public String register()
	{
		return "register";
	}
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session)
	{
		boolean check = userService.existsEmailCheck(user.getEmail());
		if(check)
		{
			session.setAttribute("msg", "email already exists");
		}
		else
		{
			User u = userService.saveUser(user);
			if(u!=null)
			{
				session.setAttribute("msg", "register Success");
			}
			else
			{
				session.setAttribute("msg", "Something Wrong");
			}	
		}
		System.out.println(user);
		return "redirect:/register";
	}
}
