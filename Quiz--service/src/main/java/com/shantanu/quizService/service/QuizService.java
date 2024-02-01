package com.shantanu.quizService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shantanu.quizService.dao.QuizDao;
import com.shantanu.quizService.feign.QuizInterface;
import com.shantanu.quizService.model.QuestionWrapper;
import com.shantanu.quizService.model.Quiz;
import com.shantanu.quizService.model.Response;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;
    
	public ResponseEntity<String> createQuiz(String categoryName, Integer numQuestions, String title) {
		// TODO Auto-generated method stub
		List<Integer> questions = quizInterface.getQuestionsForQuiz(categoryName, numQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		// TODO Auto-generated method stub
        ResponseEntity<Integer> score = quizInterface.calculateScore(responses);
        return score;
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		// TODO Auto-generated method stub
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
        return questions;
	}

}
