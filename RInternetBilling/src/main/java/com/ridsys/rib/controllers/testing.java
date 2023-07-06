package com.ridsys.rib.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.ridsys.rib.models.Nas;
import com.ridsys.rib.repository.NasRepository;

public class testing {
//	@Autowired
//	static
//	NasRepository nasRepository;
//	
//	public static void main(String args[]) {
//		Nas nasObj2 = new Nas();
//		//
//				nasObj2.setShortname("Micro");
//				ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("shortname", startsWith());
//		
//				Example<Nas> example = Example.of(nasObj2, matcher);
//		
//				List<Nas> n = nasRepository.findAll(example);
//		
//				System.out.println("ddd"+n.size());
//				for (Nas nn : n) {
//					System.out.println(nn.getNasname() + " " + nn.getPorts());
//				}
//	}
}
