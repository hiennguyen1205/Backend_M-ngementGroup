	package com.vti.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.vti.dto.UserDTO;

@Entity
@Table(name = "User", catalog = "TestingSystemExam")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "UserID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short userID;

	@Column(name = "email", length = 50, nullable = false, unique = true, updatable = false)
	private String email;

	@Column(name = "username", length = 50, nullable = false, unique = true, updatable = false)
	private String username;

	@Column(name = "`password`", length = 800, nullable = false, updatable = false)
	private String password;

	@Column(name = "firstName", length = 50, nullable = false)
	private String firstName;

	@Column(name = "lastName", length = 50, nullable = false)
	private String lastName;

	@Column(name = "phone", length = 15)
	private String phone;

	@Formula(" concat(FirstName,' ',LastName) ")
	private String fullName;

	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	private List<Group> groups;

	@Column(name = "role", nullable = false)
	private String role;

	public User() {
	}

	

	public User(short userID, String email, String username, String password, String firstName, String lastName,
			String phone, String fullName, List<Group> groups, String role) {
		super();
		this.userID = userID;
		this.email = email;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.fullName = fullName;
		this.groups = groups;
		this.role = role;
	}



	public User(short id) {
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

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserDTO toUserDTO() {
		return new UserDTO(userID, email, username, password, firstName, lastName, phone, fullName,role);
	}
}
