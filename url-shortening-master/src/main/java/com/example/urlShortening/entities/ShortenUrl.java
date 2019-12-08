package com.example.urlShortening.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_SHORTEN_URL"
        , indexes = @Index(name = "index_original_url", columnList = "original_url", unique = true)
        , uniqueConstraints = @UniqueConstraint(columnNames = "shorten_key", name = "shorten_url_uq_shorten_key"))
public class ShortenUrl {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ORIGINAL_URL")
    private String originalUrl;

    @Column(name = "REDIRECT_TYPE")
    private String redirectType;

    @Column(name = "SHORTEN_KEY")
    private String shortenKey;

    @Column(name = "HIT_COUNT")
    private Long hitCount = 0L;
    
    @CreationTimestamp
    @Column(name = "create_time_stamp", updatable = false)
    private LocalDateTime createTimeStamp;

    public ShortenUrl() {
    }

    public ShortenUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public ShortenUrl(String originalUrl, String redirectType) {
        this.originalUrl = originalUrl;
        this.redirectType = redirectType;
    }

    public ShortenUrl(String originalUrl, String redirectType, String shortenUrl) {
        this.originalUrl = originalUrl;
        this.redirectType = redirectType;
        this.shortenKey = shortenUrl;
    }

    public ShortenUrl(String originalUrl, String redirectType, String shortenKey, Long hitCount) {
		this.originalUrl = originalUrl;
		this.redirectType = redirectType;
		this.shortenKey = shortenKey;
		this.hitCount = hitCount;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(String redirectType) {
		this.redirectType = redirectType;
	}

	public String getShortenKey() {
        return shortenKey;
    }

    public void setShortenKey(String shortenKey) {
        this.shortenKey = shortenKey;
    }

    public Long getHitCount() {
		return hitCount;
	}

	public void setHitCount(Long hitCount) {
		this.hitCount = hitCount;
	}

	public LocalDateTime getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(LocalDateTime createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }
}
