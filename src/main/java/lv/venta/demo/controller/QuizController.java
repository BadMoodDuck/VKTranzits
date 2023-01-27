package lv.venta.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.demo.enums.EnumQuestionTypes;
import lv.venta.demo.models.Course;
import lv.venta.demo.models.Quiz;
import lv.venta.demo.models.QuizAnswers;
import lv.venta.demo.models.QuizQuestion;
import lv.venta.demo.services.ICourseService;
import lv.venta.demo.services.IQuizService;

@Controller
public class QuizController {

	@Autowired
	private IQuizService quizService;
	
	@Autowired
	private ICourseService courseService;
	
	Logger logger = LoggerFactory.getLogger(QuizController.class);
	
	@GetMapping("/quizes") // All Courses
	public String getAllQuizes(Model model) {
		model.addAttribute("quizes",quizService.getAllQuizes());
		return "quiz/quiz-all";
	}
	
	@GetMapping("/quiz/addNew") // All Courses
	public String getQuizAdd(Model model, Quiz quiz) {
		model.addAttribute("Courses", courseService.getAllCourses());
		return "quiz/quiz-add";
	}
	@PostMapping("/quiz/addNew")
	public String postQuizAdd(Quiz quiz, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "error";
		} else {
			quizService.insertNewQuiz(quiz);
			return "redirect:/quizes";
		}
	}
	@GetMapping("/quiz/{id}/update")
	public String getUpdateQuizById(Model model,
									  @PathVariable(name="id") int id) throws Exception {
		try {
			model.addAttribute("quiz", quizService.getQuizById(id));
			model.addAttribute("Courses", courseService.getAllCourses());
			return "quiz/quiz-update";
		} catch (Exception e){
			logger.error(e.toString());
			throw new Exception("can't find quiz");
		}	
	}

	@PostMapping("/quiz/{id}/update")
	public String postUpdateQuizById(@PathVariable(name = "id") int id, Quiz quiz, BindingResult result) throws Exception {
		if (!result.hasErrors()) {
			if (quizService.updateQuizById(id, quiz)) {
				return "redirect:/quizes";
			} else {
				logger.error(null,result.getAllErrors());
				throw new Exception("can't update");
			}
		} else {
			return "quiz/quiz-update";
		}
	}
	
	@GetMapping("/quiz/{id}/delete")
	public String postQuizDelete(Model model, 
							   	 @PathVariable(name = "id") int id) {
		try {
			quizService.deleteQuizById(id);
		}
		catch (Exception e) {
			logger.error(e.toString());
		}
		return "redirect:/quizes";
	}
	
	@GetMapping("/quiz/{id}") // Single quiz
	public String getOneQuiz(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("quiz", quizService.getQuizById(id));
		return "quiz/quiz-one";
	}
	
	@GetMapping("/quiz/{id}/addQuestion") 
	public String getQuizAddQuestion(Model model,
									 QuizQuestion quizQuestion,
									 @PathVariable(name = "id") int id) throws Exception {
		try {
			List<EnumQuestionTypes> enums = Arrays.asList(EnumQuestionTypes.values());
			model.addAttribute("questionTypes", enums);
			model.addAttribute("quizId", id);
			return "quiz/quiz-add-question";
		} catch (Exception e) {
			logger.error(e.toString());
			throw new Exception("Error at Get /quiz/{id}/addQuestion");
		}
	}
	@PostMapping("/quiz/{id}/addQuestion") 
	public String postQuizAddQuestion(QuizQuestion quizQuestion,
								      BindingResult result,
								      @PathVariable(name = "id") int id) {
		if (result.hasErrors()) { 
			System.out.println(result); 
			return "quiz/quiz-add-question";

		} else {
			System.out.println("no proble");
			quizService.insertNewQuestion(quizQuestion,id);
			return "redirect:/quiz/{id}";
		}
	}
	
	@GetMapping("/quiz/{id}/updateQuestion/{questionId}") 
	public String getQuizUpdateQuestion(Model model,
									 @PathVariable(name = "id") int id,
									 @PathVariable(name = "questionId") int questionId) throws Exception {
		try {
			model.addAttribute("quizQuestion", quizService.getQuizQuestionById(questionId));
			List<EnumQuestionTypes> enums = Arrays.asList(EnumQuestionTypes.values());
			model.addAttribute("questionTypes", enums);
			model.addAttribute("quizId", id);
			return "quiz/quiz-update-question";
		} catch (Exception e) {
			logger.error(e.toString());
			throw new Exception("Error at Get /quiz/{id}/updateQuestion/{questionId}");
		}
		
	}
	@PostMapping("/quiz/{id}/updateQuestion/{questionId}") 
	public String getQuizUpdateQuestion(QuizQuestion quizQuestion,
								      BindingResult result,
								      @PathVariable(name = "id") int id,
								      @PathVariable(name = "questionId") int questionId) {
		if (result.hasErrors()) { 
			System.out.println(result); 
			return "quiz/quiz-update-question";

		} else {
			System.out.println("no proble");
			quizService.updateQuizQuestionById(quizQuestion,questionId);
			return "redirect:/quiz/{id}";
		}
	}
	
	
	@GetMapping("/quiz/{id}/deleteQuestion/{questionId}")
	public String getDeleteQuestionById(Model model, 
										@PathVariable(name = "id") int quizId,
										@PathVariable(name = "questionId") int questionId) {
		model.addAttribute("Question", quizService.deleteQuizQuestionById(quizId, questionId));
		return "redirect:/quiz/{id}";
	}
	
	@GetMapping("/quiz/{id}/question/{questionId}/addAnswers") 
	public String getQuizQuestionAddAnswers(Model model,
											QuizAnswers quizAnswers,
											@PathVariable(name = "id") int id,
											@PathVariable(name = "questionId") int questionId) throws Exception {
		try {
			ArrayList<Boolean> list = quizService.getAvailableAnswersByQuestionId(questionId);
			model.addAttribute("boolList",  list);
			model.addAttribute("quizId", id);
			return "quiz/quiz-question-add-answers";
		} catch (Exception e) {
			logger.error(e.toString());
			throw new Exception("Error at Get /quiz/{id}/addQuestion");
		}
		
	}
	@PostMapping("/quiz/{id}/question/{questionId}/addAnswers") 
	public String postQuizQuestionAddAnswers(QuizAnswers quizAnswers, 
											 BindingResult result,
											 @PathVariable(name = "id") int id,
											 @PathVariable(name = "questionId") int questionId) {
		if (result.hasErrors()) { 
			System.out.println(result); 
			return "quiz/quiz-question-add-answers";

		} else {
			quizService.insertNewQuestionAnswer(quizAnswers, questionId);
			return "redirect:/quiz/{id}";
		}
	}
	@GetMapping("/quiz/{id}/question/{questionId}/deleteAnswer/{answerId}")
	public String getDeleteAnswerById(Model model, 
										@PathVariable(name = "id") int id,
										@PathVariable(name = "questionId") int questionId,
										@PathVariable(name = "answerId") int answerId) {

		model.addAttribute("QuizAnswer", quizService.deleteQuizAnswerById(answerId));
		return "redirect:/quiz/{id}";
	}
	
	@GetMapping("/quiz/{id}/question/{questionId}/updateAnswer/{answerId}")
	public String getUpdateAnswerById(@PathVariable(name = "id") int id,
									  @PathVariable(name = "questionId") int questionId,
									  @PathVariable(name = "answerId") int answerId,
									  Model model) throws Exception {
		try {
			ArrayList<Boolean> list = quizService.getAvailableAnswersByQuestionId(questionId);
			model.addAttribute("boolList",  list);
			model.addAttribute("quizAnswer", quizService.getQuizAnswerById(answerId));
			model.addAttribute("quizId", id);
			return "quiz/quiz-answer-update";
		} catch (Exception e) {
			logger.error(e.toString());
			throw new Exception("can't find quiz answer");
		}
	}

	// localhost:8080/course/update/{id}
	@PostMapping("/quiz/{id}/question/{questionId}/updateAnswer/{answerId}")
	public String postUpdateAnswerById(@PathVariable(name = "id") int id,
									   @PathVariable(name = "questionId") int questionId,
									   @PathVariable(name = "answerId") int answerId, 
									   QuizAnswers quizAnswer, BindingResult result) throws Exception {
		if (!result.hasErrors()) {
			if (quizService.updateQuizAnswerById(answerId, quizAnswer)) {
				return "redirect:/quiz/{id}";
			} else {
				logger.debug(null, result.getAllErrors());
				throw new Exception("can't update");
			}
		} else {
			return "quiz/quiz-answer-update";
		}
	}
	
}
