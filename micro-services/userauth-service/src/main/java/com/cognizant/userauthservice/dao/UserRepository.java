package com.cognizant.userauthservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.cognizant.userauthservice.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

	@Query(value="SELECT u from Users u where u.userName = :userName ")
	public Users findByUserName(@Param("userName") String userName) throws UsernameNotFoundException;
	
	@Query(nativeQuery = true, value = "SELECT COUNT(fv_mv_id) FROM favorite WHERE fv_us_id = :id")
	public double getFavTotal(@Param("id") int id);
}
