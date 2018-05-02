package org.zerock.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TempController {
	
	@RequestMapping(value="/display")
	public @ResponseBody ResponseEntity<byte[]> display(String file) throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Content-Type", "image/jpg");
		
		String filePath = "C:\\zzz\\upload\\" + file;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		File target = new File(filePath);
		
		FileCopyUtils.copy(new FileInputStream(target), baos);
		
		ResponseEntity<byte[]> result = new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
		
		return result;
	}

	@RequestMapping(value="/temp1", produces="image/gif")
	public @ResponseBody byte[] displayTemp() throws Exception{
		
		int value = (int)(Math.random() * 10000);
		
		String str = "T" + value;
		
		BufferedImage img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = img.getGraphics();
		
		g.setColor(Color.pink);
		g.fillRect(0, 0, 300, 300);
		
		int style = Font.BOLD | Font.ITALIC;
		
		Font font = new Font("Garamond", style , 30);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(str, 50, 150);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		ImageIO.write(img, "gif", baos);
		
		return baos.toByteArray();
	}
}
