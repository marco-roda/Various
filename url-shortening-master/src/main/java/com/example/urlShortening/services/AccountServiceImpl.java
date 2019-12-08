package com.example.urlShortening.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.urlShortening.dao.AccountRepository;
import com.example.urlShortening.dto.AccountResponseDto;
import com.example.urlShortening.entities.Account;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {

	public static final int PASSWORD_LENGTH = 8; 
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
													   "0123456789" + 
													   "abcdefghijklmnopqrstuvwxyz";

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountResponseDto openAccount(String accountId) {

		if (accountRepository.findByAccountId(accountId).isPresent()) { 
			return new AccountResponseDto(AccountResponseDto.SUCCESS_FALSE, "An account with the same ID already exists");
		} else {
			Account account = accountRepository.save(new Account(accountId, randomAlphaNumeric(PASSWORD_LENGTH)));
			return new AccountResponseDto(AccountResponseDto.SUCCESS_TRUE, "The account is successfully created", account.getPassword());
		}
	}

	@Override
	public boolean isAccountPresent(String accountId) {
		
		return accountRepository.findByAccountId(accountId).isPresent();
	}

	private static String randomAlphaNumeric(int count) {
		
		StringBuilder builder = new StringBuilder();
		
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		
		return builder.toString();
	}	
}
