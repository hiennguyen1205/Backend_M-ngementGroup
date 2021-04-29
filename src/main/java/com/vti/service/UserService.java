package com.vti.service;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vti.entity.User;
import com.vti.repository.IUserRepository;
@Service
public class UserService implements IUserService{

	@Autowired
	private IUserRepository repository;
	
	@Override
	public Page<User> getListUsers(Pageable pageable)  {
		return repository.findAll(pageable);
	}

	@Override
	public User getUserById(short id) {
		return repository.findById(id).get();
	}

	@Override
	public User getUserByName(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public void createUser(User user) {
		repository.save(user);
		
	}

	@Override
	public void updateUser(User user) {
		repository.save(user);
	}

	@Override
	public void deleteUser(short id) {
		repository.deleteById(id);
	}

	@Override
	public boolean isUserExistsByID(short id) {
		return false;
	}

	@Override
	public boolean isUserExistsByName(String username) {
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		//AuthorityUtils.createAuthorityList(user.getRole())
		//không để role thì bỏ user.getRole()
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), 
				user.getPassword(), 
				AuthorityUtils.createAuthorityList(user.getRole()));
	}

}
