package com.vti.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.UserDTO;
import com.vti.entity.User;
import com.vti.service.IUserService;

@RestController
@RequestMapping(value = "api/v1/login")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LoginController {

	@Autowired
	private IUserService service;
	
	@GetMapping()
	public UserDTO login(Principal principal) {
		User user = service.getUserByName(principal.getName());
		return new UserDTO(user.getUserID(), user.getEmail(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getFullName(),user.getRole());
	}
}
