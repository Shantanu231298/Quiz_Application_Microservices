package com.shantanu.quizService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shantanu.quizService.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
