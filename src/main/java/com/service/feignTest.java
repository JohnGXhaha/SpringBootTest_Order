package com.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("springBootTest-user")
public interface feignTest {
	@RequestMapping("/getPersonByfeigen")
	public Object getPersonByfeigen();
}
