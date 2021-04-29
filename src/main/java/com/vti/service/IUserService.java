package com.vti.service;

//import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.entity.User;

//public interface IUserService extends UserDetailsService{
public interface IUserService{
	Page<User> getListUsers(Pageable pageable);//int page, int pageSize, String fieldName, String type);

	User getUserById(short id);

	User getUserByName(String username);

	void createUser(User user);

	void updateUser(User user);

	void deleteUser(short id);

	boolean isUserExistsByID(short id);

	boolean isUserExistsByName(String username);

}
