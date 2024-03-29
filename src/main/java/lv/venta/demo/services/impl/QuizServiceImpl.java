package lv.venta.demo.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.enums.EnumQuestionTypes;
import lv.venta.demo.models.Course;
import lv.venta.demo.models.Quiz;
import lv.venta.demo.models.QuizAnswers;
import lv.venta.demo.models.QuizEmployeeAnswer;
import lv.venta.demo.models.QuizQuestion;
import lv.venta.demo.repos.IEmployeeCourseRepo;
import lv.venta.demo.repos.IEmployeeRepo;
import lv.venta.demo.repos.IQuizAnswers;
import lv.venta.demo.repos.IQuizEmployeeAnswer;
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
	@Autowired
	private IQuizEmployeeAnswer quizEmployeeAnswersRepo;
	@Autowired
	private IEmployeeRepo employeeRepo;
	
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
	@Override
	public QuizQuestion getQuizQuestionById(int id) {
		if (!quizQuestionRepo.existsById(id))
			return new QuizQuestion();
		
		return quizQuestionRepo.findById(id).get();
	}
	@Override
	public void updateQuizQuestionById(QuizQuestion quizQuestion, int id) {
		if (!quizQuestionRepo.existsById(id))
			return;
		QuizQuestion updatedQuestion = quizQuestionRepo.findById(id).get();
		updatedQuestion.setQuestion(quizQuestion.getQuestion());
		updatedQuestion.setQuestionType(quizQuestion.getQuestionType());
		quizQuestionRepo.save(updatedQuestion);
	}
	@Override
	public void deleteQuizById(int id) {
		if (!quizRepo.existsById(id))
			return;
		
		Quiz quiz = quizRepo.findById(id).get();
		for (QuizQuestion question : quiz.getQuizQuestions()) {
			for (QuizAnswers answer : question.getQuizAnswers()) {
				quizAnswersRepo.delete(answer);
			}
			quizQuestionRepo.delete(question);
		}
		quizRepo.deleteById(id);
	}
	@Override
	public boolean updateQuizById(int id, Quiz quiz) {
		if (!quizRepo.existsById(id))
			return false;
		
		Quiz updatedQuiz = quizRepo.findById(id).get();
		updatedQuiz.setTitle(quiz.getTitle());
		updatedQuiz.setCompletionDeadLine(quiz.getCompletionDeadLine());
		updatedQuiz.setCourse(quiz.getCourse());
		quizRepo.save(updatedQuiz);
		return true;
	}
	@Override
	public ArrayList<Boolean> getAvailableAnswersByQuestionId(int questionId) {
		if (!quizQuestionRepo.existsById(questionId))
			return null;
		ArrayList<Boolean> list = new ArrayList<>();
		QuizQuestion question = quizQuestionRepo.findById(questionId).get();
		if (question.getQuestionType() == EnumQuestionTypes.CHECKBOX) {
			list.add(true);
			list.add(false);
		}
		if (question.getQuestionType() == EnumQuestionTypes.RADIO) {
			list.add(false);
			boolean hasCorrect = false;
			for (QuizAnswers answer : question.getQuizAnswers()) {
				if (answer.getIsCorrect()) {
					hasCorrect = true;
				}
			}
			if (!hasCorrect)
				list.add(true);
		}
			
		
		return list;
	}
	@Override
	public QuizQuestion getQuizQuestionByQuizIdAndParam(int quizId, int questionNumber) {
		if (!quizRepo.existsById(quizId))
			return null;
		Quiz quiz = quizRepo.findById(quizId).get();
		ArrayList<QuizQuestion> questionList = new ArrayList<QuizQuestion>(quiz.getQuizQuestions()) ;
		return questionList.get(questionNumber);
	}
	@Override
	public int getQuestionSizeByQuizId(int quizId) {
		try {
			if (!quizRepo.existsById(quizId))
				return 0;
			Quiz quiz = quizRepo.findById(quizId).get();
			ArrayList<QuizQuestion> questionList = new ArrayList<QuizQuestion>(quiz.getQuizQuestions()) ;
			return questionList.size();
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
		
	}
	@Override
	public void insertNewQuizEmployeeAnswer(int userId,int quizId,int question,QuizEmployeeAnswer quizEmployeeAnswer) {
		try {
			quizEmployeeAnswer.setEmployee(employeeRepo.findById(userId).get());
			quizEmployeeAnswer.setQuiz(quizRepo.findById(quizId).get());
			quizEmployeeAnswer.setQuizQuestion(getQuizQuestionByQuizIdAndParam(quizId, question));
			quizEmployeeAnswersRepo.save(quizEmployeeAnswer);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
