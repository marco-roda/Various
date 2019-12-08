package com.example.urlShortening.controller;

import com.example.urlShortening.dto.OriginalUrlDto;
import com.example.urlShortening.dto.ShortenUrlDto;
import com.example.urlShortening.services.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UrlShorteningController {

    @Autowired
    private ShortenUrlService urlService;

    @PostMapping("/register")
    public ResponseEntity<ShortenUrlDto> registerUrl(@Validated @RequestBody OriginalUrlDto originalUrlDto) {
    	
        return ResponseEntity.ok(new ShortenUrlDto(urlService.shortenUrl(originalUrlDto.getUrl(), originalUrlDto.getRedirectType())));
    }

}
