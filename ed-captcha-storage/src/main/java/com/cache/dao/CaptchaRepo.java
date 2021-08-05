package com.cache.dao;

import java.util.Map;

import com.cache.dto.CaptchaDet;

public interface CaptchaRepo {

	// Save a new captcha.
	void save(CaptchaDet employee);

	// Find captcha by txnId.
	CaptchaDet findById(String txnId);

	// Final all txnId.
	Map<String, CaptchaDet> findAll();

	// Delete a txnId.
	void delete(String id);
}
