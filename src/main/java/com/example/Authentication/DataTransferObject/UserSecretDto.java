package com.example.Authentication.DataTransferObject;

public class UserSecretDto {

	private Long userId;
    private String username;
    private String email;

	public UserSecretDto(Long userId, String username, String email) {
		this.userId = userId;
		this.username = username;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public Long getUserId() {
		return userId;
	}
}

