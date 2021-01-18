package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Prac2Dto;
import com.mycompany.webapp.service.PracService;


public class Prac2Controller {

	@GetMapping("/modelLogin")
	public String modelLogin(Model model) {
		model.addAttribute("id","jyhoon94");
		model.addAttribute("pw","Zpflrjs94!");
		
		return "prac/prac";
	}
	
	@GetMapping("/modelLogin2")
	public String modelLogin2(Model model) {
		Prac2Dto dto = new Prac2Dto();
		
		dto.setId("jyhoon94");
		dto.setPw("Zpflrjs94!");
		
		model.addAttribute("dto",dto);
		
		return "prac/prac";
	}
	
	@GetMapping("/modelLogin3")
	public String modelLogin3(Model model) {
		List <Prac2Dto> list = new ArrayList<>();
		
		Prac2Dto dto = new Prac2Dto();
		
		for(int i=1; i<10; i++) {
			dto.setId("id"+i);
			dto.setPw("pw"+i);
			list.add(dto);
		}
		
		model.addAttribute("list",list);
		return "prac/prac";
	}
	

	
	
	
	
}
