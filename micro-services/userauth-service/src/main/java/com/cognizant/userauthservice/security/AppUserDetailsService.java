package com.cognizant.userauthservice.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.userauthservice.dao.UserRepository;
import com.cognizant.userauthservice.exception.UserAlreadyExistsException;
import com.cognizant.userauthservice.model.Role;
import com.cognizant.userauthservice.model.Users;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	@Autowired 
	PasswordEncoder encoder;
	
	public AppUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AppUser appUser;
		Users user = userRepository.findByUserName(username);
		if(user.getUserName() == null) {
			throw new UsernameNotFoundException("User not found"); 
		}else {
			appUser = new AppUser(user);  
		}
		return appUser; 
	}
	
	public void signup(Users user) throws UserAlreadyExistsException {
		if(userRepository.findByUserName(user.getUserName()) != null) {
			throw new UserAlreadyExistsException();
		}else {
			Set<Role> roleList = new HashSet();
			roleList.add(new Role(2,"user"));
			user.setRoleList(roleList);
			userRepository.save(user);
		}
		
	}
	
	

}
