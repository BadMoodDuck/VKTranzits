package lv.venta.demo.services.impl;

import java.util.ArrayList;
import java.util.Iterator;

import javax.validation.Valid;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Course;
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
	@Override
	public boolean deleteQuizAnswerById(int answerId) {
		if (quizAnswersRepo.existsById(answerId)) {
			quizAnswersRepo.deleteById(answerId);
			return true;
		}
		return false;
	}
	@Override
	public QuizAnswers getQuizAnswerById(int id) {
		if (quizAnswersRepo.existsById(id)) {
			QuizAnswers qa = quizAnswersRepo.findById(id).get();
			return qa;
		}
		return null;
	}
	@Override
	public boolean updateQuizAnswerById(int id, QuizAnswers quizAnswer) {
		QuizAnswers result = new QuizAnswers();
		if (quizAnswersRepo.existsById(id)) {
			result = quizAnswersRepo.findById(id).get();
			result.setAnswer(quizAnswer.getAnswer());
			result.setIsCorrect(quizAnswer.getIsCorrect());
			quizAnswersRepo.save(result);
			return true;
		} else {
		return false;
		}
	}
	@Override
	public boolean deleteQuizQuestionById(int quizId, int questionId) {

		if (!quizRepo.existsById(quizId)) 
			return false;
		if (!quizQuestionRepo.existsById(questionId))
			return false;
		
		Quiz quiz = quizRepo.findById(quizId).get();
		QuizQuestion quizQuestion = quizQuestionRepo.findById(questionId).get();
		for (QuizAnswers answer : quizQuestion.getQuizAnswers()) {
			quizAnswersRepo.delete(answer);			
		}
		quizQuestionRepo.delete(quizQuestion);
		quizRepo.save(quiz);
		
		return true;
		
	}
}
