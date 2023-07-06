package com.ridsys.rib.comm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ridsys.rib.models.Userinfo;

public class SyslogApi {

	public static String userCreationToSyslog() {

		Userinfo userinfo = new Userinfo();

		userinfo.setUsername("testsub");
		userinfo.setPassword("testsub");
		userinfo.setAddress("Pondycherry");
		userinfo.setCity("Pondycherry");
		userinfo.setState("Pondycherry");
		userinfo.setEmail("qc@gmail.com");
		userinfo.setMobilephone("7339391013");
		userinfo.setFirstname("QC");
		userinfo.setLastname("USER");
		userinfo.setOpid(1);

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.add("Header", "header1");

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://192.168.1.107:8080/SysLog/UserInfoInsert.do?")
				.queryParam("mobilephone", userinfo.getMobilephone()).queryParam("email", userinfo.getEmail())
				.queryParam("username", userinfo.getUsername()).queryParam("address", userinfo.getAddress())
				.queryParam("city", userinfo.getCity()).queryParam("state", userinfo.getState())
				.queryParam("opid", userinfo.getOpid()).queryParam("firstname", userinfo.getFirstname())
				.queryParam("lastname", userinfo.getLastname()).queryParam("password", userinfo.getPassword());

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				String.class);
		System.out.println("coming into ott userinfo" + response);
		return response.getBody();
	}

	public static String userModificationToSyslog() {

		RestTemplate restTemplate = new RestTemplate();

		Userinfo userinfo = new Userinfo();

		userinfo.setUsername("testsub");
		userinfo.setPassword("testsub");
		userinfo.setAddress("Chennai");
		userinfo.setCity("Chennai");
		userinfo.setState("Tamilnadu");
		userinfo.setEmail("qc@gmail.com");
		userinfo.setMobilephone("7339391013");
		userinfo.setFirstname("QC");
		userinfo.setLastname("USER");
		userinfo.setOpid(1);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.add("Header", "header1");

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://192.168.1.107:8080/SysLog/UserInfoEdit.do?")
				.queryParam("mobilephone", userinfo.getMobilephone()).queryParam("email", userinfo.getEmail())
				.queryParam("username", userinfo.getUsername()).queryParam("address", userinfo.getAddress())
				.queryParam("city", userinfo.getCity()).queryParam("state", userinfo.getState())
				.queryParam("opid", userinfo.getOpid()).queryParam("firstname", userinfo.getFirstname())
				.queryParam("lastname", userinfo.getLastname());

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				String.class);
		System.out.println("coming into ott userinfo" + response);
		return response.getBody();
	}
}
