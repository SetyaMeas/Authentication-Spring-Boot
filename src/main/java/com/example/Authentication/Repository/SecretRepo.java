package com.example.Authentication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Authentication.DataTransferObject.LoginDto;
import com.example.Authentication.DataTransferObject.UserSecretDto;
import com.example.Authentication.Entity.*;

public interface SecretRepo extends JpaRepository<Secret, Long> {

	@Query(value = """
		select new com.example.Authentication.DataTransferObject.LoginDto
		(s.userId, s.email,s.pwd)
		from Secret s
		where s.email = :email
	""")
	LoginDto findByEmailForLogin(@Param("email") String email);

	@Query(value = """
		select new com.example.Authentication.DataTransferObject.UserSecretDto
		(s.userId, s.user.username, s.email)
	 	from Secret s
		where s.email = :email
	""")
	UserSecretDto findByEmail(@Param("email") String email);
}
