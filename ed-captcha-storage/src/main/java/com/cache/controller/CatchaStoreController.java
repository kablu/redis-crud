package com.cache.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cache.dto.CaptchaDet;
import com.cache.service.CaptchaService;

@RestController
@RequestMapping(value = "/api/redis/captcha")
public class CatchaStoreController {

	private static final Logger LOG = LoggerFactory.getLogger(CatchaStoreController.class);

	@Autowired
	CaptchaService service;

	// Save a new captcha.
	// Url - http://localhost:10091/api/redis/employee
	@PostMapping
	public String save(@RequestBody final CaptchaDet catcha) {
		LOG.info("Saving the new catcha to the redis.");
		service.save(catcha);
		return "Successfully added. Employee with id= " + catcha.getTxnId();
	}

	// Get all captcha.
	// Url - http://localhost:10091/api/redis/employee/getall
	@GetMapping("/getall")
	public Map<String, CaptchaDet> findAll() {
		LOG.info("Fetching all employees from the redis.");
		final Map<String, CaptchaDet> employeeMap = service.findAll();
		// Todo - If developers like they can sort the map (optional).
		return employeeMap;
	}

	// Get captcha by id.
	// Url - http://localhost:10091/api/redis/employee/get/<employee_id>
	@GetMapping("/get/{id}")
	public CaptchaDet findById(@PathVariable("id") final String id) {
		LOG.info("Fetching employee with id= " + id);
		return service.findById(id);
	}

	// Delete captcha by id.
	// Url - http://localhost:10091/api/redis/employee/delete/<employee_id>
	@DeleteMapping("/delete/{id}")
	public Map<String, CaptchaDet> delete(@PathVariable("id") final String id) {
		LOG.info("Deleting employee with id= " + id);
		// Deleting the employee.
		service.delete(id);
		// Returning the all employees (post the deleted one).
		return findAll();
	}
}
