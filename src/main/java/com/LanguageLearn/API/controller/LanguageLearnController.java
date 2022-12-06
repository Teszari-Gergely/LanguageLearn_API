package com.LanguageLearn.API.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.LanguageLearn.API.Service.LanguageLearnService;
import com.LanguageLearn.API.model.WordPair;

@RestController
@RequestMapping("/api/WordPair")
public class LanguageLearnController {
	@Autowired
	private LanguageLearnService service;
	
	@GetMapping
	@ResponseBody
	public List<WordPair> getWords(@RequestParam(required = false) Integer Lecture, 
			@RequestParam(required = false) String Tags)
	{
		return service.getFiveWords(Lecture, Tags);
	}
	
	
	@PostMapping
	@ResponseBody
	public Integer addNewWord(
			@RequestBody(required=true) WordPair wordPair,
			@RequestParam(required = true) String pwd)
	{
		return service.addWord(wordPair, pwd);
	}
	
	
	@PostMapping
	@ResponseBody
	@RequestMapping("/pwd")
	public Integer addOrUpdatePWD(@RequestParam(required = true) String oldPWD, 
			@RequestParam(required = true) String newPWD)
	{
		return service.pwdModify(oldPWD, newPWD);
	}
}
