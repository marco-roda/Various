package com.example.urlShortening.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(	name = "TBL_ACCOUNT",
		indexes = @Index(name = "index_account_id", columnList = "account_id", unique = true))
public class Account {
	
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ACCOUNT_ID")
    private String accountId;

    @Column(name = "PASSWORD")
    private String password;

    @CreationTimestamp
    @Column(name = "create_time_stamp", updatable = false)
    private LocalDateTime createTimeStamp;

    public Account() {
    }

	public Account(String accountId, String password) {
		this.accountId = accountId;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreateTimeStamp() {
		return createTimeStamp;
	}

	public void setCreateTimeStamp(LocalDateTime createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}

}
