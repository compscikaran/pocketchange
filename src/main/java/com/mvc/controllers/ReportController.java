package com.mvc.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;


import com.mvc.services.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService service;
	
	@RequestMapping("/report")
	public ResponseEntity<byte[]> getReport()
	{
		byte[] content;
		String filename = service.generateReport();
		try {
			content = Files.readAllBytes(Paths.get(filename));
			HttpHeaders headers = new HttpHeaders();
			headers.add("content-type", "application/pdf");
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
			return response;
		} catch (IOException e) {
			content = "Could not generate report".getBytes();
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(content, HttpStatus.OK);
			return response;
		}
		
	}
}
