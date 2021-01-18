package com.mycompany.webapp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Qualifier("Ch13BoardDao6Qualifier")
public class Ch13BoardDao6 implements Ch13BoardDaoInterface {

	private static final Logger logger = LoggerFactory.getLogger(Ch13BoardDao6.class);
	@Override
	public void insert() {
		logger.info("실행");
	}

}
