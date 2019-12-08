package com.example.urlShortening.services;

import com.example.urlShortening.entities.ShortenUrl;

import java.util.Map;
import java.util.Optional;

public interface ShortenUrlService {

    String shortenUrl(String originalUrl, String redirectType);

    Optional<ShortenUrl> getShortenUrl(String shortenKey);

	void incrementHitCount(String shortenKey);

	Map<String, Long> getStatistic();
}
