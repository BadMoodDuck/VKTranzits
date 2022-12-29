package lv.venta.demo.services;

import java.util.ArrayList;

import javax.validation.Valid;

import lv.venta.demo.models.Quiz;
import lv.venta.demo.models.QuizAnswers;
import lv.venta.demo.models.QuizQuestion;

public interface IQuizService {

	Quiz getQuizById(int id);

	void insertNewQuestion(QuizQuestion question, int id);

	void insertNewQuestionAnswer(QuizAnswers quizAnswers, int questionId);

	ArrayList<Quiz> getAllQuizes();

	void insertNewQuiz(Quiz quiz);

	boolean deleteQuizAnswerById(int answerId);

	QuizAnswers getQuizAnswerById(int id);

	boolean updateQuizAnswerById(int id, QuizAnswers quizAnswer);


}
