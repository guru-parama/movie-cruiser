package com.cognizant.moviecruiser.testing;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cognizant.moviecruiser.dao.UserRepository;
import com.cognizant.moviecruiser.exception.UserAlreadyExistsException;
import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.model.Role;
import com.cognizant.moviecruiser.model.Users;
import com.cognizant.moviecruiser.security.AppUserDetailsService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserDetailsServiceMockTest {

	@Autowired
	AppUserDetailsService userService;
	@Test
	public void mockTestLoadUserByUsername() {
		UserRepository repository = Mockito.mock(UserRepository.class);
	when(repository.findByUserName("parama")).thenReturn(createUser());
		//when(repository.findByUserName("para")).thenThrow(UsernameNotFoundException.class);
		AppUserDetailsService service = new AppUserDetailsService(repository);
		
		UserDetails user = service.loadUserByUsername("parama");
        String expected = "$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK";
        assertEquals(expected, user.getPassword());
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void mockTestLoadUserByUsernameException() {
		UserRepository repository = Mockito.mock(UserRepository.class);
		Users user = createUser();
		user.setUserName(null);
		when(repository.findByUserName("parama")).thenReturn(user);
		AppUserDetailsService service = new AppUserDetailsService(repository);
		service.loadUserByUsername("parama");
	}
	 
	private Users createUser() {
		Set<Role> roleList = new HashSet<Role>();
		roleList.add(new Role(1,"admin"));
		Users user = new Users("param","parama","guru", "$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK");
		user.setId(1);
		user.setMovieList(new HashSet<Movie>());
		user.setRoleList(roleList);
		return user;
	}
	
	@Test(expected = UserAlreadyExistsException.class)
	public void mockSignup() throws UserAlreadyExistsException  {
		UserRepository repository = Mockito.mock(UserRepository.class);
		Set<Role> roleList = new HashSet<Role>();
		roleList.add(new Role(1,"admin"));
		Users user = new Users("parama","parama","guru", "$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK");
		user.setMovieList(new HashSet<Movie>());
		user.setRoleList(roleList);
		when(repository.findByUserName("parama")).thenReturn(user);
		AppUserDetailsService service = new AppUserDetailsService(repository);
		service.signup(user);
	}
	
	@Test
	public void signup() throws UserAlreadyExistsException {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		Set<Role> roleList = new HashSet<Role>();
		roleList.add(new Role(2,"user"));
		Users user = new Users("ram","parama","guru", "$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK");
		user.setMovieList(new HashSet<Movie>());
		user.setRoleList(roleList);
		when(userRepository.findByUserName("ram")).thenReturn(null);
		AppUserDetailsService service = new AppUserDetailsService(userRepository);
		service.signup(user);
		assertEquals(true, true);
				
	}
	
	
}
