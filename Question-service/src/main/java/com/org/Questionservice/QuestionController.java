package com.org.Questionservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.Questionservice.model.Question;
import com.org.Questionservice.model.QuestionWrapper;
import com.org.Questionservice.model.Response;
import com.org.Questionservice.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

	@Autowired
	QuestionService service;
	
	/*
	 * @GetMapping("allQuestions") public List<Question> getAllQuestion(){ return
	 * service.getAllQuestions(); }
	 */
	@GetMapping("allquestion")
	public ResponseEntity<List<Question>> Result() {
		return service.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
		return service.getQuestionByCategory(category);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> AddQuestion(@RequestBody Question question) {
		
		return service.addQuestion(question);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> DeleteQuestion(@PathVariable("id") int id) {
		return service.deleteQuestion(id);
	}
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQuestions){
		return service.getQuestionsforQuiz(category,numQuestions);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionids){
		return service.getQuestionsforQuizFromId(questionids);
	}
	@PostMapping("getScore")
	public ResponseEntity<Integer> calculateScore(@RequestParam List<Response> responses){
		return service.getScore(responses);
	}
}
