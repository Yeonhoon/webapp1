package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.mycompany.webapp.dao.Ch14OrderDao;
import com.mycompany.webapp.dto.Ch14OrderDto;
import com.mycompany.webapp.dto.Ch14OrderItemDto;

@Service
public class Ch14OrderService {
	
	@Resource 
	private Ch14OrderDao dao;
	
	@TransactionalEventListener
	public void order (Ch14OrderDto order, List<Ch14OrderItemDto> orderItems){
		//orders 테이블에 주문 정보 저장
		dao.insertOrder(order);
		
		//생성된 주문번호(위에 실행해서 getOno 가능)
		int ono = order.getOno();
		
		//Orderitems 테이블에 주문상품 정보 저장
		for(Ch14OrderItemDto oi: orderItems) {
			oi.setOno(ono);
			dao.insertOrderItem(oi);
		}
	}
	

	
	
}
