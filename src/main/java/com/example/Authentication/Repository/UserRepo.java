package com.example.Authentication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Authentication.Entity.User;

import jakarta.transaction.Transactional;

public interface UserRepo extends JpaRepository<User, Long> {

	@Transactional
	@Modifying
	@Query(value = "update User u set u.username = :username where u.userId = :userId")
	void updateUsername(@Param("userId") Long userId, @Param("username") String username);
}
