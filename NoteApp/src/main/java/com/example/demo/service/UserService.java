package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService
{
	public User saveUser(User user);
	public boolean existsEmailCheck(String email);
	public void remoceSessionMsg();
}