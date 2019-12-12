package com.cognizant.userauthservice.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name= "user")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "us_id")
	private int id;
	@Column(name = "us_userName")
	private String userName;
	@Column(name = "us_firstName")
	private String fisrtName;
	@Column(name = "us_lastName")
	private String lastName;
	@Column(name = "us_password")
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
			joinColumns = @JoinColumn(name = "ur_us_id"),
			inverseJoinColumns = @JoinColumn(name="ur_ro_id"))
	private Set<Role> roleList;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "favorite",
			joinColumns = @JoinColumn(name = "fv_us_id"),
			inverseJoinColumns = @JoinColumn(name="fv_mv_id"))
	private Set<Movie> movieList;
	
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUserName() {
		return userName;
	}
	public Users(String userName, String fisrtName, String lastName, String password) {
		super();
		this.userName = userName;
		this.fisrtName = fisrtName;
		this.lastName = lastName;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFisrtName() {
		return fisrtName;
	}
	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(Set<Role> roleList) {
		this.roleList = roleList;
	}
	public Set<Movie> getMovieList() {
		return movieList;
	}
	public void setMovieList(Set<Movie> movieList) {
		this.movieList = movieList;
	}
	
	
}
