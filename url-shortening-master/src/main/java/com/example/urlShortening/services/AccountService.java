package com.example.urlShortening.services;

import com.example.urlShortening.dto.AccountResponseDto;

public interface AccountService {

	AccountResponseDto openAccount(String accountId);

	boolean isAccountPresent(String accountId);

}
