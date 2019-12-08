package com.example.urlShortening.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.urlShortening.dto.AccountRequestDto;
import com.example.urlShortening.dto.AccountResponseDto;
import com.example.urlShortening.services.AccountService;

@RestController
@RequestMapping("/")
public class AccountController {
	
	private final InMemoryUserDetailsManager inMemoryUserDetailsManager;	

    @Autowired
    private AccountService accountService;
	
    @Autowired
    public AccountController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
       this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @PostMapping("/account")
    public ResponseEntity<AccountResponseDto> openAccount(@Validated @RequestBody AccountRequestDto accountRequestDto) {

    	AccountResponseDto accountResponseDto = accountService.openAccount(accountRequestDto.getAccountId());
    	
    	if (accountResponseDto.getSuccess().equals(AccountResponseDto.SUCCESS_TRUE)) {
    		createUser(accountRequestDto.getAccountId(), accountResponseDto.getPassword());
    	}
    	
    	return ResponseEntity.ok(accountResponseDto);
    }

    private void createUser(String username, String password) {
    	
		ArrayList<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		grantedAuthoritiesList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		inMemoryUserDetailsManager.createUser(new User(username, "{noop}"+password, grantedAuthoritiesList));
    }

}
