package lv.venta.demo.services.impl;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Quiz;
import lv.venta.demo.models.QuizAnswers;
import lv.venta.demo.models.QuizQuestion;
import lv.venta.demo.repos.IQuizAnswers;
import lv.venta.demo.repos.IQuizQuestion;
import lv.venta.demo.repos.IQuizRepo;
import lv.venta.demo.services.IQuizService;

@Service
public class QuizServiceImpl implements IQuizService{

	@Autowired
	private IQuizRepo quizRepo;
	@Autowired
	private IQuizQuestion quizQuestionRepo;
	@Autowired
	private IQuizAnswers quizAnswersRepo;
	
	
	@Override
	public Quiz getQuizById(int id) {
		if (quizRepo.existsById(id)) {
			return quizRepo.findById(id).get();
		}
		return null;
	}
	@Override
	public void insertNewQuestion(QuizQuestion question, int quizId) {
		System.out.println(quizId);
		if (quizRepo.existsById(quizId)) {
			Quiz quiz = quizRepo.findById(quizId).get();
			question.setQuiz(quiz);
			quizQuestionRepo.save(question);
			
		}
	}
	@Override
	public void insertNewQuestionAnswer(QuizAnswers quizAnswers, int questionId) {
		System.out.println("AT: insterNewQuestionAnswer");
		if (quizQuestionRepo.existsById(questionId)) {
			QuizQuestion question = quizQuestionRepo.findById(questionId).get();
			quizAnswers.setQuizQuestion(question);
			quizAnswersRepo.save(quizAnswers);
		}
		
	}
	@Override
	public ArrayList<Quiz> getAllQuizes() {
		
		return (ArrayList<Quiz>) quizRepo.findAll();
	}
	@Override
	public void insertNewQuiz(Quiz quiz) {
		quizRepo.save(quiz);
	}

}
