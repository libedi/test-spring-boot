package com.libedi.test.common.service;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Rest API Request Service
 * @author Sangjun, Park
 *
 */
@Service
public class RestApiRequestService {
	
	private static final Logger logger = LoggerFactory.getLogger(RestApiRequestService.class);
	
	private final String SSL_PORT = "443";
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * REST API 요청 전송 (POST)
	 * @param domain 요청 도메인 주소 (ex: http://www.test.com)
	 * @param port 요청 도메인 포트 (ex: 8080)
	 * @param reqUri 서브 요청 URI (ex: /impaytest/test)
	 * @param reqStr JSON 형태의 요청 문자열
	 * @return ResponseEntity
	 */
	public ResponseEntity<String> restSendByPost(String domain, String port, String reqUri, String reqStr){
		return this.sendToRestAPI(domain, port, reqUri, reqStr, HttpMethod.POST);
	}
	
	/**
	 * REST API 요청 전송 (GET)
	 * @param domain 요청 도메인 주소 (ex: http://www.test.com)
	 * @param port 요청 도메인 포트 (ex: 8080)
	 * @param reqUri 서브 요청 URI (ex: /impaytest/test)
	 * @param reqStr JSON 형태의 요청 문자열
	 * @return ResponseEntity
	 */
	public ResponseEntity<String> restSendByGet(String domain, String port, String reqUri, String reqStr){
		return this.sendToRestAPI(domain, port, reqUri, reqStr, HttpMethod.GET);
	}
	
	/**
	 * REST API SSL 요청 전송 (POST)
	 * @param domain 요청 도메인 주소 (ex: http://www.test.com)
	 * @param port 요청 도메인 포트 (ex: 8080)
	 * @param reqUri 서브 요청 URI (ex: /impaytest/test)
	 * @param reqStr JSON 형태의 요청 문자열
	 * @return ResponseEntity
	 */
	public ResponseEntity<String> restSslSendByPost(String domain, String port, String reqUri, String reqStr){
		// TODO SSL 로직 추가
		return this.restSendByPost(domain, port, reqUri, reqStr);
	}
	
	/**
	 * API 서버 URL 생성
	 * @param domain
	 * @param port
	 * @param reqUri
	 * @return 요청 URL
	 */
	private String getRequestUrl(String domain, String port, String reqUri){
		StringBuilder url = new StringBuilder();
		if(!Pattern.compile("^(https?:\\/\\/).*").matcher(domain).matches()){
			url.append("http://");
		} else if(StringUtils.isEmpty(port) && Pattern.compile("^(https:\\/\\/).*").matcher(domain).matches()){
			port = SSL_PORT;
		}
		url.append(domain);
		if(StringUtils.isNotEmpty(port)){
			url.append(":").append(port);
		}
		if(!Pattern.compile("^\\/.*").matcher(reqUri).matches()){
			url.append("/");
		}
		return url.append(reqUri).toString();
	}

	/**
	 * REST API 요청
	 * @param domain
	 * @param port
	 * @param reqUri
	 * @param reqStr
	 * @param httpMethod
	 * @return ResponseEntity
	 */
	private ResponseEntity<String> sendToRestAPI(String domain, String port, String reqUri, String reqStr, HttpMethod httpMethod){
		String url = this.getRequestUrl(domain, port, reqUri);
		logger.info("REST REQUEST - URI : {}", url);
		logger.info("REST REQUEST - STR : {}", reqStr);
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        HttpEntity<String> httpEntity = new HttpEntity<String>(reqStr, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, httpEntity, String.class);
        logger.info("REST RESPONSE - STATUS CODE: {}, REASONS: {}", response.getStatusCode().value(), response.getStatusCode().getReasonPhrase());
		return response;
	}

}
