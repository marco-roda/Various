package com.example.urlShortening.dto;

import javax.validation.constraints.NotEmpty;

public class AccountResponseDto {
	
	public static final String SUCCESS_TRUE = Boolean.TRUE.toString();
	public static final String SUCCESS_FALSE = Boolean.FALSE.toString();

    @NotEmpty
    private String success;

    @NotEmpty
    private String description;

    private String password;

    public AccountResponseDto() {
    }

	public AccountResponseDto(String success, String description) {
		this.success = success;
		this.description = description;
	}

	public AccountResponseDto(String success, String description, String password) {
		this.success = success;
		this.description = description;
		this.password = password;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
