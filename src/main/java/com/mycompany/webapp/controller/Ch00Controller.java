package com.mycompany.webapp.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch00")
public class Ch00Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch00Controller.class);

	@RequestMapping("/content")
	public String content() {
		logger.info("initiate");
		return "ch00/content";
	}
	//
}
