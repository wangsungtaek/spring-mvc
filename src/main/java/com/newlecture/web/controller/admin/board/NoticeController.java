package com.newlecture.web.controller.admin.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController {
	
	@Autowired
	private ServletContext ctx;
	
	@RequestMapping("list")
	public String list() {
		
		return "admin.board.notice.list";
	}
	
	@GetMapping("reg")
	public String reg() {
		
		return "admin.board.notice.reg";
	}
	
	@PostMapping("reg")
	public String reg(String title, String content, MultipartFile[] files, String category, String[] foods, String food) throws IllegalStateException, IOException {
		
		for(MultipartFile file : files) {
			long size = file.getSize();
			String fileName = file.getOriginalFilename();
			System.out.printf("fileName:%s, fileSize:%d\n", fileName, size);
			
			String webPath = "/static/upload";
			String realPath = ctx.getRealPath(webPath);
			System.out.printf("realPath : %s\n", realPath);
			
			// 업로드하기 위한 경로가 없을 경우
			File savePath = new File(realPath);
			if(!savePath.exists())
				savePath.mkdirs();
			
			realPath += File.separator+fileName;
			File saveFile = new File(realPath);
			file.transferTo(saveFile);
		}
		
		for(String f : foods)
			System.out.println(f);
		
		return "admin.board.notice.reg";
	}
	
	@RequestMapping("edit")
	public String edit() {
		
		return "admin.board.notice.edit";
	}
	
	@RequestMapping("del")
	public String del() {
		
		return "admin.board.notice.del";
	}
	
	
}
