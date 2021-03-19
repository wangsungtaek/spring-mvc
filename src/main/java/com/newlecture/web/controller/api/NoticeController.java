package com.newlecture.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notice/")
public class NoticeController {
	
	@RequestMapping("list")
	public String list() {
		
		return "notice list";
	}
}
