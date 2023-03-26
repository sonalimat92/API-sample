package com.demo.bluejay.helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class HelperSuite {
	public final String UPLOAD_DIR="./src/main/resources/static/";
	
	public boolean uploadFile(MultipartFile multipartFile) {
		boolean f=false;
		try {
			Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+multipartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			f=true;
			//return f;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return f;
		
	}

}
