package com.mycompany.webapp.dao;


import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Ch14OrderDto;
import com.mycompany.webapp.dto.Ch14OrderItemDto;

@Repository
public class Ch14OrderDao {
	private static final Logger logger = LoggerFactory.getLogger(Ch14OrderDao.class);
	
	@Resource
	private SqlSessionTemplate sst;
	
	
	public int insertOrder(Ch14OrderDto order) {
		int rows = sst.insert("orders.orderinsert",order);
		return rows;
	}
	
	public int insertOrderItem(Ch14OrderItemDto orderitem) {
		int rows = sst.insert("orders.orderiteminsert", orderitem);
		return rows;
		
	}
	
}
