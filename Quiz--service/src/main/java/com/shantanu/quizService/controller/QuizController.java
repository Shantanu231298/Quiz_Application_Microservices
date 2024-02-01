package com.shantanu.quizService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shantanu.quizService.model.QuestionWrapper;
import com.shantanu.quizService.model.QuizDto;
import com.shantanu.quizService.model.Response;
import com.shantanu.quizService.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	private QuizService qservice;
	
	@PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return qservice.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
    }

    @PostMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return qservice.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return qservice.calculateResult(id, responses);
    }
}
