package com.ridsys.rib.comm;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ridsys.rib.models.Ottplanmanagelog;
import com.ridsys.rib.models.Userinfo;

public class OttApi {

	public static String userCreationOtt(Userinfo userinfo) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Header", "header1");

		UriComponentsBuilder builder = UriComponentsBuilder
//				.fromHttpUrl("http://udigitalott.ridsys.in:8080/R-OTT/userCreate.do?")
//				.fromHttpUrl("http://192.168.40.35:8080/R-OTT/userCreate.do?")
//				.fromHttpUrl("http://localhost:8080/R-OTT/userCreate.do?")
//				.fromHttpUrl("http://192.168.1.105:8080/R-OTT/userCreate.do?")
				.fromHttpUrl("http://192.168.50.32:8585/R-OTT/userCreate.do?")
				.queryParam("name", userinfo.getFirstname() + " " + userinfo.getLastname())
				.queryParam("mobile", userinfo.getMobilephone()).queryParam("email", userinfo.getEmail())
				.queryParam("username", userinfo.getUsername()).queryParam("password", userinfo.getPassword())
				.queryParam("city", userinfo.getCity()).queryParam("creationdate", userinfo.getCreationdate());

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				String.class);
		System.out.println("coming into ott userinfo" + response);
		return response.getBody();
	}

	public static String userActiveDeactive(String username, Boolean status, Userinfo userinfo) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Header", "header1");

		UriComponentsBuilder builder = UriComponentsBuilder
//				.fromHttpUrl("http://udigitalott.ridsys.in:8080/R-OTT/userPermissionn.do?")
//				.fromHttpUrl("http://192.168.40.35:8080/R-OTT/userPermissionn.do?")
				.fromHttpUrl("http://192.168.50.32:8585/R-OTT/userPermissionn.do?")
//				.fromHttpUrl("http://192.168.1.105:8080/R-OTT/userPermissionn.do?")
//				.fromHttpUrl("http://localhost:8080/R-OTT/userPermissionn.do?")
				.queryParam("name", userinfo.getFirstname() + " " + userinfo.getLastname())
				.queryParam("mobile", userinfo.getMobilephone()).queryParam("email", userinfo.getEmail())
				.queryParam("username", userinfo.getUsername()).queryParam("password", userinfo.getPassword())
				.queryParam("city", userinfo.getCity()).queryParam("creationdate", userinfo.getCreationdate())
				.queryParam("status", status);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				String.class);

		return response.getBody();
	}

	public static String planActivation(Ottplanmanagelog obj, int packageid) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Header", "header1");

		UriComponentsBuilder builder = UriComponentsBuilder
//				.fromHttpUrl("http://udigitalott.ridsys.in:8080/R-OTT/planActivationLog.do?")
//				.fromHttpUrl("http://192.168.40.35:8080/R-OTT/planActivationLog.do?")
				.fromHttpUrl("http://192.168.50.32:8585/R-OTT/planActivationLog.do?")
//				.fromHttpUrl("http://192.168.1.105:8080/R-OTT/planActivationLog.do?")
//				.fromHttpUrl("http://localhost:8080/R-OTT/planActivationLog.do?")
				.queryParam("referenceid", obj.getRechargeid()).queryParam("username", obj.getUsername())
				.queryParam("amount", obj.getPlancost()).queryParam("expirydate", obj.getExpirydate())
				.queryParam("packageid", packageid).queryParam("plandays", obj.getPlandays());

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				String.class);

		System.out.println(response);

		return response.getBody();
	}

	public static String planDectivation(Ottplanmanagelog obj) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Header", "header1");

		UriComponentsBuilder builder = UriComponentsBuilder
//				.fromHttpUrl("http://udigitalott.ridsys.in:8080/R-OTT/planDeactivationLog.do?")
//				.fromHttpUrl("http://192.168.40.35:8080/R-OTT/planDeactivationLog.do?")
				.fromHttpUrl("http://192.168.50.32:8585/R-OTT/planDeactivationLog.do?")
//				.fromHttpUrl("http://192.168.1.105:8080/R-OTT/planDeactivationLog.do?")
//				.fromHttpUrl("http://localhost:8080/R-OTT/planDeactivationLog.do?")
				.queryParam("referenceid", obj.getRechargeid()).queryParam("username", obj.getUsername())
				.queryParam("amount", obj.getPlancost()).queryParam("expirydate", obj.getExpirydate())
				.queryParam("packageid", obj.getPackageid()).queryParam("plandays", obj.getPlandays());

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				String.class);

		System.out.println("response" + response);

		return response.getBody();
	}

	public static String forgotPassword(String newmob, String oldmob) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Header", "header1");

		UriComponentsBuilder builder = UriComponentsBuilder
//				.fromHttpUrl("http://udigitalott.ridsys.in:8080/R-OTT/planDeactivationLog.do?")
//				.fromHttpUrl("http://192.168.40.35:8080/R-OTT/forgotPassword.do?")
				.fromHttpUrl("http://192.168.50.32:8585/R-OTT/forgotPassword.do?")
//				.fromHttpUrl("http://localhost:8080/R-OTT/forgotPassword.do?")
//				.fromHttpUrl("http://localhost:8081/R-OTT/forgotPassword.do?")
				.queryParam("oldmobile", oldmob).queryParam("newmobile", newmob);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				String.class);

		System.out.println("response" + response);

		return response.getBody();
	}

}
