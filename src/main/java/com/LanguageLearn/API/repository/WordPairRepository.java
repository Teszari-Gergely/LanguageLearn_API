package com.LanguageLearn.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.LanguageLearn.API.model.WordPair;

public interface WordPairRepository extends JpaRepository<WordPair, Long> {}
