package lv.venta.demo.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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
import lv.venta.demo.services.IQuizService;

@Controller
public class QuizController {

	@Autowired
	private IQuizService quizService;
	
	
	@GetMapping("/quizes") // All Courses
	public String getAllQuizes(Model model) {
		model.addAttribute("quizes",quizService.getAllQuizes());
		return "quiz-all";
	}
	
	@GetMapping("/quiz/addNew") // All Courses
	public String getQuizAdd(Model model, Quiz quiz) {
		return "quiz-add";
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
	
	@GetMapping("/quiz/{id}") // Single quiz
	public String getOneQuiz(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("quiz", quizService.getQuizById(id));
		return "quiz-one";
	}
	
	@GetMapping("/quiz/{id}/addQuestion") 
	public String getQuizAddQuestion(Model model,
									 QuizQuestion quizQuestion,
									 @PathVariable(name = "id") int id) throws Exception {
		try {
			List<EnumQuestionTypes> enums = Arrays.asList(EnumQuestionTypes.values());
			model.addAttribute("questionTypes", enums);
			model.addAttribute("quizId", id);
			return "quiz-add-question";
		} catch (Exception e) {
			throw new Exception("Error at Get /quiz/{id}/addQuestion");
		}
		
	}
	@PostMapping("/quiz/{id}/addQuestion") 
	public String postQuizAddQuestion(QuizQuestion quizQuestion,
								      BindingResult result,
								      @PathVariable(name = "id") int id) {
		if (result.hasErrors()) { 
			System.out.println(result); 
			return "quiz-add-question";

		} else {
			System.out.println("no proble");
			quizService.insertNewQuestion(quizQuestion,id);
			return "redirect:/quiz/{id}";
		}
	}
	
	
	@GetMapping("/quiz/{id}/question/{questionId}/addAnswers") 
	public String getQuizQuestionAddAnswers(Model model,
											QuizAnswers quizAnswers,
											@PathVariable(name = "id") int id,
											@PathVariable(name = "questionId") int questionId) throws Exception {
		try {
			Boolean[] list = {true, false};
			model.addAttribute("boolList",  list);
			model.addAttribute("quizId", id);
			model.addAttribute("questionId", questionId);
			return "quiz-question-add-answers";
		} catch (Exception e) {
			throw new Exception("Error at Get /quiz/{id}/addQuestion");
		}
		
	}
	@PostMapping("/quiz/{id}/question/{questionId}/addAnswers") 
	public String postQuizQuestionAddAnswers(QuizAnswers quizAnswers, 
											 BindingResult result,
											 @PathVariable(name = "id") int id,
											 @PathVariable(name = "questionId") int questionId) {
		System.out.println("POST");
		if (result.hasErrors()) { 
			System.out.println(result); 
			return "quiz-question-add-answers";

		} else {
			System.out.println("no proble");
			quizService.insertNewQuestionAnswer(quizAnswers, questionId);
			return "redirect:/quiz/{id}";
		}
	}
}
