package com.example.urlShortening.services;

import com.example.urlShortening.dao.ShortenUrlRepository;
import com.example.urlShortening.entities.ShortenUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Service
public class ShortenUrlServiceImpl implements ShortenUrlService {

	public static final String DEFAULT_REDIRECT_TYPE = "302";

    @Value("${base.url}")
    private String BASE_URL;

    @Value("${shorten.url.prefix}")
    private String SHORTEN_URL_PREFIX;

    @Autowired
    private ShortenUrlRepository shortenUrlRepository;

    @Autowired
    private IdConverterService idConverterService;

    @Override
    public String shortenUrl(String originalUrl, String redirectType) {
    	
    	String redirect = validateRedirectType (redirectType);
    	
        return BASE_URL.concat(SHORTEN_URL_PREFIX)
        		.concat(shortenUrlRepository.findByOriginalUrl(originalUrl)
                .orElseGet(() -> saveAndShorten(originalUrl, redirect))
                .getShortenKey());
    }

    private ShortenUrl saveAndShorten(String originalUrl, String redirectType) {
    	
        ShortenUrl url = shortenUrlRepository.save(new ShortenUrl(originalUrl, redirectType));
        
        String shortenKey = idConverterService.encode(url.getId());
        url.setShortenKey(shortenKey);

        return url;
    }

    @Override
    public Optional<ShortenUrl> getShortenUrl(String shortenKey) {
    	
        return shortenUrlRepository.findById(idConverterService.decode(shortenKey));
    }

    @Override
    public void incrementHitCount(String shortenKey) {	

    	getShortenUrl(shortenKey).ifPresent(url -> incrementHitCountAndSave(url));
    }
    
    private void incrementHitCountAndSave(ShortenUrl url) {
    	
    	url.setHitCount(url.getHitCount()+1);
    	shortenUrlRepository.save(url);
    }

    @Override
    public Map<String, Long> getStatistic() {
    	
    	List<ShortenUrl> urlList = shortenUrlRepository.findAll();

    	Map<String, Long> urlMap = new HashMap<String, Long>();
    	urlList.forEach(shortenUrl -> urlMap.put(shortenUrl.getOriginalUrl(), shortenUrl.getHitCount()));

    	return urlMap;
    }

    public static String validateRedirectType(String redirectType) {
    	
    	if (redirectType == null) 
    		return DEFAULT_REDIRECT_TYPE;
    	
    	if (redirectType.equals("301") || redirectType.equals("302"))
    		return redirectType;
    	
    	return DEFAULT_REDIRECT_TYPE;
    }
    
}
