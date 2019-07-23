package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.codingapi.tx.annotation.TxTransaction;

@RestController
public class springcloudTestController {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@RequestMapping("/cacheDeleteOne/{id}")
//	@RequestMapping("/redisfindOne/{id}")
	@RequestMapping("/getPersonList")
	/**
	  *分布式事务 服务发起方
	  *其中 @TxTransaction(isStart = true) 为lcn 事务控制注解，其中isStart = true 表示该方法是事务的发起方例如，
	  *服务A 需要调用服务B,服务B 需要调用服务C，
	  *此时 服务A为服务发起方，其余为参与方，参与方只需@Transaction 即可  
	 * */
	@TxTransaction(isStart = true)
//	public Object getPerson(@PathVariable Long id) {
		public Object getPerson() {
		
//		return restTemplate.getForObject("http://127.0.0.1:8086/hello/cacheDeleteOne?id="+id, Person.class);
//		return restTemplate.getForObject("http://127.0.0.1:8086/hello/redisfindOne?id="+id, Person.class);
		return restTemplate.getForObject("http://springBootTest-user/hello/getPersonList", Object.class);
//		restTemplate.get
//		return (List) restTemplate.getForObject("http://127.0.0.1:8086/hello/getPersonList", );
	}
}
