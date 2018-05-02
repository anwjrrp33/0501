package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/up/*")
@Log4j
public class UploadController {
	
	@GetMapping("/form")
	public void uploadForm() {
		log.info("uploadForm get");
	}
	
	@PostMapping(value="/ajax", produces="application/json")
	public @ResponseBody ResponseEntity<List<String>> uploadAjaxFiles(MultipartFile[] file) throws Exception{
		log.info(file);
		
		List<String> uploadNames = new ArrayList<>();
		
		for (MultipartFile upfile : file) {
			log.info(upfile.getOriginalFilename());
			log.info("-----------------------------");
			
			UUID uid = UUID.randomUUID();
			
			String uploadFileName = uid.toString() + "_" + upfile.getOriginalFilename();
			
			FileOutputStream fos = new FileOutputStream(new File("C:\\zzz\\upload", uploadFileName));
			
			FileCopyUtils.copy(upfile.getInputStream(), fos);
			fos.close();
			
			//make thumbnail
			
			FileOutputStream thfos = new FileOutputStream(new File("C:\\zzz\\upload", "s_" + uploadFileName));
			
			Thumbnailator.createThumbnail(upfile.getInputStream(), thfos, 100, 100);
			
			thfos.close();
			
			uploadNames.add(uploadFileName);
		}
		
		return new ResponseEntity<List<String>>(uploadNames, HttpStatus.OK);
	}
	
	@PostMapping("/form")
	public void uploadFiles(MultipartFile[] file) throws Exception{
		log.info(file);
		
		for (MultipartFile upfile : file) {
			log.info(upfile.getOriginalFilename());
			log.info("-----------------------------");
			
			UUID uid = UUID.randomUUID();
			
			FileOutputStream fos = new FileOutputStream(new File("C:\\zzz\\upload", upfile.getOriginalFilename()));
			FileCopyUtils.copy(upfile.getInputStream(), fos);
		}
	}
	
	
	@GetMapping("/ajax")
	public void uploadAjax() {
		
		log.info("upload ajax");
	}
	
	

}
	