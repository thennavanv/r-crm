package com.ridsys.rib.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ridsys.rib.repository.DictionaryRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/dic")
public class DictionaryController {

	
	private DictionaryRepository dictionaryRepository;

	public DictionaryController(DictionaryRepository dictionaryRepository) {
		super();
		this.dictionaryRepository = dictionaryRepository;
	}

	@GetMapping("/matchvendors/{searchvalue}")
	public List<String> getMatchvendors(@PathVariable String searchvalue) {		
		return dictionaryRepository.findByVendorLike(searchvalue);

	}
	
	@GetMapping("/matchattributes/{searchvalue}")
	public List<String> getMatchattributes(@PathVariable String searchvalue) {
		return dictionaryRepository.findByAttributeLike(searchvalue);

	}
}
