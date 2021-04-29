package com.vti.controller;

//import java.util.ArrayList;
//import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.UserDTO;
import com.vti.entity.User;
import com.vti.service.IUserService;

@RestController
@RequestMapping(value = "api/v1/users")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
//@EnableGlobalMethodSecurity(
//		  prePostEnabled = true, 
//		  securedEnabled = true, 
//		  jsr250Enabled = true)
//@PreAuthorize("hasAnyAuthority('Admin')")

public class UserController {
	@Autowired
	private IUserService service;

	@GetMapping
	public Page<UserDTO> getAllUser(Pageable pageable) {
		Page<User> pageUsers = service.getListUsers(pageable);
		
		Page<UserDTO> pageUserDTO = pageUsers.map(new Function<User, UserDTO>() {

			@Override
			public UserDTO apply(User user) {
				UserDTO userDTO = new UserDTO(
						user.getUserID(),
						user.getEmail(),
						user.getUsername(),
						user.getPassword(),
						user.getFirstName(),
						user.getLastName(),
						user.getPhone(),
						user.getFullName(),
						user.getRole());
				return userDTO;
			}
		});
		
		return pageUserDTO;
	}
	
	@GetMapping(value = "/list_users_name")
	public Page<UserDTO> getAllUserName(Pageable pageable) {
		Page<User> pageUsers = service.getListUsers(pageable);
		
		Page<UserDTO> pageUserDTO = pageUsers.map(new Function<User, UserDTO>() {

			@Override
			public UserDTO apply(User user) {
				UserDTO userDTO = new UserDTO(user.getUserID(),user.getUsername(),user.getFullName());
				return userDTO;
			}
		});
		
		return pageUserDTO;
	}
	
	@GetMapping(value = "/{id}")
	private UserDTO getUserById(@PathVariable(name = "id") short id) {
		if(service.getUserById(id)==null) return null;
		UserDTO userDTO = service.getUserById(id).toUserDTO();
		return userDTO;
	}
	
	@GetMapping(value = "username/{name}")
	private UserDTO getUserByName(@PathVariable(name = "name") String name) {
		UserDTO userDTO = service.getUserByName(name).toUserDTO();
		return userDTO;
	}

	@PostMapping
	private String createUser(@RequestBody UserDTO userDTO) {
		service.createUser(userDTO.convertToUser());
		return"{\"message\":" + "\"Create Successfully\"" + "}";
	}

	@PutMapping(value = "/{id}")
	private String updateUser(@PathVariable(name = "id") short id, @RequestBody UserDTO userDTO) {
		User user = userDTO.convertToUser();
		user.setUserID(id);
		service.updateUser(user);
		return "{\"message\":" + "\"Update Successfully\"" + "}";
	}

	@DeleteMapping(value = "/{id}")
	private String deleteUser(@PathVariable(name = "id") short id) {
		service.deleteUser(id);
		return "{\"message\":" + "\"Delete Successfully\"" + "}";
	}
}
