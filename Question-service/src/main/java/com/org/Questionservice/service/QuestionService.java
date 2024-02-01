package com.org.Questionservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.org.Questionservice.dao.QuestionDao;
import com.org.Questionservice.model.Question;
import com.org.Questionservice.model.QuestionWrapper;
import com.org.Questionservice.model.Response;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao dao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		// TODO Auto-generated method stub
		try {
		return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		// TODO Auto-generated method stub
		
		try {
			return new ResponseEntity<>(dao.findByCategory(category), HttpStatus.OK);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}

	public ResponseEntity<String> addQuestion(Question question) {
		// TODO Auto-generated method stub
		dao.save(question);
		try {
			return new ResponseEntity<>("Success",HttpStatus.CREATED);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<String> deleteQuestion(int id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
		try {
			return new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<List<Integer>> getQuestionsforQuiz(String category, Integer numQuestions) {
		// TODO Auto-generated method stub
		List<Integer> questions= dao.getRandomQuestionByCategory(category, numQuestions);
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsforQuizFromId(List<Integer> questionids) {
		// TODO Auto-generated method stub
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		
		for(Integer id: questionids) {
			questions.add(dao.findById(id).get());
		}
		for(Question question: questions) {
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			
			wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		// TODO Auto-generated method stub
		int right=0;
		for(Response response: responses) {
			Question question= dao.findById(response.getId()).get();
			if(response.getResponse().equals(question)) {
				right++;
			}
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
	
	
	
	
}
