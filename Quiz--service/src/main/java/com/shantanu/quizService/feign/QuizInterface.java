package com.shantanu.quizService.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.shantanu.quizService.model.QuestionWrapper;
import com.shantanu.quizService.model.Response;



@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
	
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQuestions);
	
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionids);

	@PostMapping("question/getScore")
	public ResponseEntity<Integer> calculateScore(@RequestParam List<Response> responses);
	
}

