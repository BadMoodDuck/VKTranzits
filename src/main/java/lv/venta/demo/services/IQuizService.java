package lv.venta.demo.services;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.Quiz;
import lv.venta.demo.models.QuizAnswers;
import lv.venta.demo.models.QuizEmployeeAnswer;
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

	boolean deleteQuizQuestionById(int quizId, int questionId);

	QuizQuestion getQuizQuestionById(int id);

	void updateQuizQuestionById(QuizQuestion quizQuestion, int id);

	void deleteQuizById(int id);

	boolean updateQuizById(int id, Quiz quiz);

	ArrayList<Boolean> getAvailableAnswersByQuestionId(int questionId);

	QuizQuestion getQuizQuestionByQuizIdAndParam(int quizId, int questionNumber);

	int getQuestionSizeByQuizId(int quizId);

	void insertNewQuizEmployeeAnswer(int userId, int quizId, int question, QuizEmployeeAnswer quizEmployeeAnswer);

}
