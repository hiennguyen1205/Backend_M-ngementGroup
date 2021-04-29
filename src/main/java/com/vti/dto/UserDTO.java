package com.vti.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;

import com.vti.entity.Group;
import com.vti.entity.User;

public class UserDTO {

	private short userID;

	@Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Nhập email đúng định dạng mới được. VD: abcxyz@gmail.com")
	private String email;

	@Size(max = 50, message = "Độ dài tối đa 50 ký tự")
	private String username;

	@Size(max = 800, message = "Tối đa 8000 ký tự")
	private String password;

	@NotEmpty(message = "First name phải khác empty")
	@NotBlank(message = "First name phải có ký tự khác khoảng trắng")
	@Size(max = 50, message = "First name dài nhất có 50 ký tự")
	private String firstName;

	@NotEmpty(message = "Last name phải khác empty")
	@NotBlank(message = "Last name phải có ký tự khác khoảng trắng")
	@Size(max = 50, message = "Last name dài nhất có 50 ký tự")
	private String lastName;

	@NotEmpty(message = "Phone phải khác empty")
	@NotBlank(message = "Phone phải có ký tự khác khoảng trắng")
	@Size(max = 15, message = "Phone dài nhất có 15 ký tự")
	private String phone;

	@Formula(" concat(FirstName,' ',LastName) ")
	private String fullName;

	private List<Group> groups;

	private String role;

	public UserDTO() {
	}

	

	public UserDTO(short userID,
			@Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Nhập email đúng định dạng mới được. VD: abcxyz@gmail.com") String email,
			@Size(max = 50, message = "Độ dài tối đa 50 ký tự") String username,
			@Size(max = 800, message = "Tối đa 8000 ký tự") String password,
			@NotEmpty(message = "First name phải khác empty") @NotBlank(message = "First name phải có ký tự khác khoảng trắng") @Size(max = 50, message = "First name dài nhất có 50 ký tự") String firstName,
			@NotEmpty(message = "Last name phải khác empty") @NotBlank(message = "Last name phải có ký tự khác khoảng trắng") @Size(max = 50, message = "Last name dài nhất có 50 ký tự") String lastName,
			@NotEmpty(message = "Phone phải khác empty") @NotBlank(message = "Phone phải có ký tự khác khoảng trắng") @Size(max = 15, message = "Phone dài nhất có 15 ký tự") String phone,
			String fullName, String role) {
		this.userID = userID;
		this.email = email;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.fullName = fullName;
		this.role = role;
	}



	public UserDTO(short userID, @Size(max = 50, message = "Độ dài tối đa 50 ký tự") String username,
			String fullName) {
		super();
		this.userID = userID;
		this.username = username;
		this.fullName = fullName;
	}



	public short getUserID() {
		return userID;
	}

	public void setUserID(short userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User convertToUser() {
		return new User(userID, email, username, password, firstName, lastName, phone, fullName, groups,role);
	}
}
