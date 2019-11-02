package io.simpolor.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/sender")
public class SenderController {

	/**
	 *  DELETE : delete
	 *  GET : getForObject, getForEntity
	 *  HEAD : headForHeaders
	 *  OPTIONS : optionsForAllow
	 *  POST : postForLocation, postForObject
	 *  PUT : put
	 *  any : exchange, execute
	 */

	@RequestMapping("/get")
	public String senderGet() {
		
		log.info("senderGet");

		RestTemplate restTemplate = new RestTemplate();
		
		URI uri = UriComponentsBuilder.fromHttpUrl("http://127.0.0.1:8080/reciver/get")
					.queryParam("name", "abc")
					.build()
					.toUri();
		
		String response = restTemplate.getForObject(uri, String.class);
		log.info("> response : {}", response);

		if(response.equals("success")) {
			return  "success";
		}
		
		return "failed";
	}
	
	@ResponseBody
	@RequestMapping("/sender/rest/getId")
	public String senderRestGetId(HttpServletRequest request) {
		
		System.out.println("-- SenderController > senderRestGetId");
		
		String result = "fail";
		
		RestTemplate restTemplate = new RestTemplate();

		
		UriComponentsBuilder build = UriComponentsBuilder.fromHttpUrl("http://127.0.0.1:8080//receiver/rest/get/seokyoung");
		build.queryParam("name", "abc");
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authority", "smilegate");
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
													build.toUriString()
													, HttpMethod.GET
													, entity
													, String.class
												);
		
		System.out.println("> response : "+response);
		System.out.println("> response.getStatusCode() : "+response.getStatusCode());

		if(HttpStatus.OK.equals(response.getStatusCode())) {
			result = "success";
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/sender/rest/post")
	public String senderRestPost(HttpServletRequest request) {
		
		System.out.println("-- SenderController > senderRestPost");
		
		String result = "fail";
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "http://127.0.0.1:8080//receiver/rest/post";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authority", "smilegate");
		
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("name", "abcd");
		params.add("age", "19");
		
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);


		ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
		if(HttpStatus.OK.equals(response.getStatusCode())) {
			result = "success";
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/sender/rest/file")
	public String senderRestFile(HttpServletRequest request) {
		
		System.out.println("-- SenderController > senderRestFile");
		
		String result = "fail";
		
		String fileName = "dlpan.jpg";
		String filePath = "upload";
		String uploadFilePath = request.getServletContext().getRealPath(filePath.concat(File.separator).concat(fileName));
		//File uploadFile = new File(uploadFilePath);
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "http://127.0.0.1:8080//receiver/rest/file";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.set("Authority", "smilegate");
		
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("name", "abcd");
		params.add("age", "19");
		params.add("upload_file", new FileSystemResource(uploadFilePath));
		
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
		if(HttpStatus.OK.equals(response.getStatusCode())) {
			result = "success";
		}
		
		return result;
	}
	
}
