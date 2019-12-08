package com.example.urlShortening.dto;

import javax.validation.constraints.NotEmpty;

public class AccountRequestDto {

    @NotEmpty
    private String accountId;

    public AccountRequestDto() {
    }

	public AccountRequestDto(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
