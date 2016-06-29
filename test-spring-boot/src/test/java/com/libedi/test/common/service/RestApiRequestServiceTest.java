/*
 * Copyright (c) 2013 SK planet.
 * All right reserved.
 *
 * This software is the confidential and proprietary information of SK planet.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SK planet.
 */
package com.libedi.test.common.service;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libedi.test.common.model.ApiTestRequest;
import com.libedi.test.common.model.ApiTestResponse;

public class RestApiRequestServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(RestApiRequestServiceTest.class);

	private RestApiRequestService restApiRequestService;
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp() throws Exception{
		restApiRequestService = new RestApiRequestService();
		restTemplate = mock(RestTemplate.class);
		
		ReflectionTestUtils.setField(restApiRequestService, "restTemplate", restTemplate);
	}
	
	@Test
	public void testMockCreation() throws Exception{
		assertNotNull(restApiRequestService);
		assertNotNull(restTemplate);
	}
	
	@Test
	public void testRestApiRequest() throws Exception{
		// Parameter Object
		ApiTestRequest apiTestRequest = new ApiTestRequest("Peter", "01012345678", "IT_DEV");
		
		// JSON request string
		String reqStr = this.objectMapper.writeValueAsString(apiTestRequest);
		// HTTP request
		ResponseEntity<String> response = restApiRequestService.restSendByPost("http://api.test.com", "8080", "/test/request", reqStr);
		// response OK
		if(HttpStatus.OK == response.getStatusCode()){
			String resStr = response.getBody();
			ApiTestResponse apiTestResponse = this.objectMapper.readValue(resStr, ApiTestResponse.class);
			if(StringUtils.equals("0000", apiTestResponse.getResCode())){
				logger.debug("success!");
			}
		}
	}

}
