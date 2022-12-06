package com.LanguageLearn.API.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="word_pair")
public class WordPair {
	private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long ID;
	private String Local;
	private String Native;
	private Integer Lecture;
	private String Tags;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getLocal() {
		return Local;
	}
	public void setLocal(String local) {
		Local = local;
	}
	public String getNative() {
		return Native;
	}
	public void setNative(String native1) {
		Native = native1;
	}
	public Integer getLecture() {
		return Lecture;
	}
	public void setLecture(Integer lecture) {
		Lecture = lecture;
	}
	public String getTags() {
		return Tags;
	}
	public void setTags(String tags) {
		Tags = tags;
	}
	public List<String> tagsToList()
	{
		return Arrays.asList(Tags.split(","));
	}
}
