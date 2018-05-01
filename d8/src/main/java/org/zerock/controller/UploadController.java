package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/up/*")
@Log4j
public class UploadController {
	
	@GetMapping("/form")
	public void uploadForm() {
		log.info("uploadForm get");
	}
	
	@PostMapping("/form")
	public void uploadFiles(MultipartFile[] file) throws Exception{
		log.info(file);
		
		for (MultipartFile upfile : file) {
			log.info(upfile.getOriginalFilename());
			log.info("-----------------------------");
			
			UUID uid = UUID.randomUUID();
			
			FileOutputStream fos = new FileOutputStream(new File("C:\\zzz\\upload",upfile.getOriginalFilename()));
			FileCopyUtils.copy(upfile.getInputStream(), fos);
		}
	}
	
	
	@GetMapping("/ajax")
	public void uploadAjax() {
		
		log.info("upload ajax");
	}
	
	

}
	