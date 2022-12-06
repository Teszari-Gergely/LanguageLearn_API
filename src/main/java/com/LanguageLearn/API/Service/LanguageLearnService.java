package com.LanguageLearn.API.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.LanguageLearn.API.model.WordPair;
import com.LanguageLearn.API.repository.WordPairRepository;

import Password.SingletonPassword;


@Service
public class LanguageLearnService 
{	
	@Autowired
	private WordPairRepository repository;
	
	@Transactional
	public WordPair getProperty(Long Id){
		Optional<WordPair> WP = repository.findById(Id);
		if (WP.isPresent()) {
			WordPair wordPair = WP.get();
			return wordPair;
		}
		return null;
	}
	
	@Transactional
	public List<WordPair> getFiveWords(Integer Lecture, String Tags)
	{
		List<WordPair> words = repository.findAll();
		if (Lecture != null)
		{
			words = words.stream().filter(word -> word.getLecture()==Lecture).toList();
			
		}
		if (Tags != null)
		{
			var TagsList = Arrays.asList(Tags.split(","));
			
			words = words.stream().filter(word -> {
				for (String Tag: word.tagsToList()) {
					if (TagsList.contains(Tag))
					{
						return true;
					}
				}
				return false;
			}).toList();
			
		}
		if (words.size() < 5) return Collections.emptyList();
		Collections.shuffle(words);
		return words.subList(0, 5);
	}
	
	public Integer addWord(WordPair wp, String pwd)
	{
		String currentPWD = SingletonPassword.getInstance();
		if (pwd == null) {
			if (currentPWD.equals("")) {
				repository.save(wp);
				return Response.SC_OK;
			}
			return Response.SC_BAD_REQUEST;
		}
		if (pwd.equals(currentPWD))
		{
			try 
			{
				repository.save(wp);
				return Response.SC_OK;
			}
			catch(Exception e)
			{
				return 418;
			}
		}
		return Response.SC_FORBIDDEN;
	}
	
	@Transactional
	public int pwdModify(String oldPWD, String newPWD)
	{
		String currentPWD = SingletonPassword.getInstance();
		if (oldPWD.equals(currentPWD))
		{
			SingletonPassword.setInstance(newPWD);
			return Response.SC_OK;
		}
		return Response.SC_FORBIDDEN;
	}
}
