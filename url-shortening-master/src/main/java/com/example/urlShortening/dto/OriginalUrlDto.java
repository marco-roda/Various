package com.example.urlShortening.dto;


import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

public class OriginalUrlDto {

    @NotEmpty(message = "{val.err.original.url.not.empty.or.null}")
    @URL(message = "{val.err.original.url.invalid}")
    private String originalUrl;
    
    private String redirectType;

    public String getUrl() {
        return originalUrl;
    }

    public void setUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

	public String getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(String redirectType) {
		this.redirectType = redirectType;
	}
}
