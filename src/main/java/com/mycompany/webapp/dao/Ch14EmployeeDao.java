package com.mycompany.webapp.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.controller.Ch13Controller;
import com.mycompany.webapp.dto.Ch14EmployeeDto;

@Repository
public class Ch14EmployeeDao {
	private static final Logger logger = LoggerFactory.getLogger(Ch13Controller.class);
	
	@Resource
	private SqlSessionTemplate sst;
	
	public Ch14EmployeeDto selectByPk(int employee_id) {
		Ch14EmployeeDto emp = sst.selectOne("mybatis.mapper.employees.selectByPk", employee_id);
		return emp;
	};
	
	
}
