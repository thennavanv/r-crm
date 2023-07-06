package com.ridsys.rib.comm;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONValue;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ridsys.rib.models.Operators;
import com.ridsys.rib.models.Userinfo;

public class IptvApi {
	public static void OperatorCreation(Operators opobj) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		URI url = null;
		try {
			url = new URI("http://localhost:8083/api/v1/operator/create");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("opid", String.valueOf(opobj.getId()));
		map.put("username", opobj.getUsername());
		map.put("password", opobj.getPassword());
		map.put("firstname", opobj.getFirstname());
		map.put("lastname", opobj.getLastname());
		map.put("address", opobj.getAddress());
		map.put("area", opobj.getArea());
		map.put("state", opobj.getState());
		map.put("country", "India");
		map.put("zip_code", opobj.getZipcode());
		map.put("phone1", opobj.getPhone1());
		map.put("email1", opobj.getEmail1());
		map.put("create_date", opobj.getCreationdate());

		String jsontext = JSONValue.toJSONString(map);
		HttpEntity<String> entity = new HttpEntity<String>(jsontext, headers);
		ResponseEntity<?> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	}

	public static void SubscriberCreation(Userinfo usrobj,boolean value) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		URI url = null;
		try {
			url = new URI("http://localhost:8083/api/v1/subscriber/create");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("opid", String.valueOf(usrobj.getOpid()));
		map.put("subid", String.valueOf(usrobj.getId()));
		map.put("username", usrobj.getUsername());
		map.put("password", usrobj.getPassword());
		map.put("firstname", usrobj.getFirstname());
		map.put("lastname", usrobj.getLastname());
		map.put("address", usrobj.getAddress());
		map.put("mobilephone", usrobj.getState());
		map.put("email", usrobj.getEmail());
		map.put("create_date", usrobj.getCreationdate());
		map.put("value", String.valueOf(value));


		String jsontext = JSONValue.toJSONString(map);
		HttpEntity<String> entity = new HttpEntity<String>(jsontext, headers);

		ResponseEntity<?> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	}

	public static void SubscriberCreationForSMS(Userinfo usrobj) {

//		RestTemplate restTemplate = new RestTemplate();
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		URI url = null;
//		try {
//			url = new URI("http://localhost:8080/R-SMS/SubscriberCreate");
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("lco_id", String.valueOf(usrobj.getOpid()));
		map.put("casform_id", IdGen.cafIdGen("cafformid"));
		map.put("subscriber_name", usrobj.getFirstname());
		map.put("last_name", usrobj.getLastname());
		map.put("dob", "1997-10-08");
		map.put("billing_address", usrobj.getAddress());
		map.put("install_address", usrobj.getAddress());
		map.put("mobile_no", usrobj.getMobilephone());
		map.put("email", usrobj.getEmail());
		map.put("aadhaar_no", IdGen.cafIdGen("aadhar"));

		System.out.println(map);

//		String jsontext = JSONValue.toJSONString(map);
//		HttpEntity<String> entity = new HttpEntity<String>(jsontext, headers);
//
//		ResponseEntity<?> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	}

}
