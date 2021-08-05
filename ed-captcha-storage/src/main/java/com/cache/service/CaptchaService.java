package com.cache.service;

import java.util.Map;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.cache.dao.CaptchaRepo;
import com.cache.dto.CaptchaDet;

@Service
public class CaptchaService implements CaptchaRepo {

	private final String EMPLOYEE_CACHE = "CAPTCHA";

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, CaptchaDet> hashOperations;

	@PostConstruct
	private void intializeHashOperations() {
		hashOperations = redisTemplate.opsForHash();
	}
	@Override
	public void save(final CaptchaDet catchaDet) {
		hashOperations.put(EMPLOYEE_CACHE, catchaDet.getTxnId(), catchaDet);
	}
	@Override
	public CaptchaDet findById(final String txnId) {
		return (CaptchaDet) hashOperations.get(EMPLOYEE_CACHE, txnId);
	}

	@Override
	public Map<String, CaptchaDet> findAll() {
		return hashOperations.entries(EMPLOYEE_CACHE);
	}

	@Override
	public void delete(String txnId) {
		hashOperations.delete(EMPLOYEE_CACHE, txnId);
	}
}
