package com.example.urlShortening.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.urlShortening.services.AccountService;
import com.example.urlShortening.services.ShortenUrlService;

@Controller
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private AccountService accountService;
	
    @Autowired
    private ShortenUrlService urlService;

	
	@GetMapping("/{accountId}")
	public ResponseEntity<Map<String, Long>> getStatistic(@PathVariable("accountId") String accountId) {
		
		if (!accountService.isAccountPresent(accountId)) {
			return new ResponseEntity<Map<String, Long>>(HttpStatus.BAD_REQUEST);
		}

		Map<String, Long> urlMap = urlService.getStatistic();
		
		if (urlMap.isEmpty()) {
			return new ResponseEntity<Map<String, Long>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Map<String, Long>>(urlMap, HttpStatus.OK);
	}	

}
