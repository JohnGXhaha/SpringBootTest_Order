package com.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//import com.codingapi.tx.annotation.TxTransaction;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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
//	@TxTransaction(isStart = true)
	/**
	 * 熔断
	 * */
	@HystrixCommand(fallbackMethod="getPersonFallback")
//	public Object getPerson(@PathVariable Long id) {
	public Object getPerson() {
		Object userList_obj=restTemplate.getForObject("http://springBootTest-user/hello/getPersonList", Object.class);
//		return restTemplate.getForObject("http://127.0.0.1:8086/hello/cacheDeleteOne?id="+id, Person.class);
//		return restTemplate.getForObject("http://127.0.0.1:8086/hello/redisfindOne?id="+id, Person.class);
		Map<String, Object> map=new HashMap<>();
		map.put("code", 0);
		map.put("data", userList_obj);

		return map;
//		restTemplate.get
//		return (List) restTemplate.getForObject("http://127.0.0.1:8086/hello/getPersonList", );
	}
	
	public Object getPersonFallback() {
		Map<String, Object> map=new HashMap<>();
		map.put("code", -1);
		map.put("msg", "当前访问数过大,请稍后再试.");
		return map;
	}
}
