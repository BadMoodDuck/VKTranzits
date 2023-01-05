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
import lv.venta.demo.services.ICourseService;
import lv.venta.demo.services.IQuizService;

@Controller
public class QuizController {

	@Autowired
	private IQuizService quizService;
	
	@Autowired
	private ICourseService courseService;
	
	
	@GetMapping("/quizes") // All Courses
	public String getAllQuizes(Model model) {
		model.addAttribute("quizes",quizService.getAllQuizes());
		return "quiz-all";
	}
	
	@GetMapping("/quiz/addNew") // All Courses
	public String getQuizAdd(Model model, Quiz quiz) {
		model.addAttribute("Courses", courseService.getAllCourses());
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
	@GetMapping("/quiz/{id}/update")
	public String getUpdateCourseById(Model model,
									  @PathVariable(name="id") int id) throws Exception {
		try {
			model.addAttribute("quiz", quizService.getQuizById(id));
			model.addAttribute("Courses", courseService.getAllCourses());
			return "quiz-update";
		} catch (Exception e){
			throw new Exception("can't find course");
		}
		
	}

	// localhost:8080/course/update/{id}
	@PostMapping("/quiz/{id}/update")
	public String postUpdateCourseById(@PathVariable(name = "id") int id, Quiz quiz, BindingResult result) throws Exception {
		if (!result.hasErrors()) {
			if (quizService.updateQuizById(id, quiz)) {
				return "redirect:/quizes";
			} else {
				throw new Exception("can't update");
			}
		} else {
			return "course-update";
		}
	}
	
	@GetMapping("/quiz/{id}/delete")
	public String postQuizDelete(Model model, 
							   	 @PathVariable(name = "id") int id) {
		quizService.deleteQuizById(id);
		return "redirect:/quizes";
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
	
	@GetMapping("/quiz/{id}/updateQuestion/{questionId}") 
	public String getQuizUpdateQuestion(Model model,
									 @PathVariable(name = "id") int id,
									 @PathVariable(name = "questionId") int questionId) throws Exception {
		try {
			model.addAttribute("quizQuestion", quizService.getQuizQuestionById(questionId));
			List<EnumQuestionTypes> enums = Arrays.asList(EnumQuestionTypes.values());
			model.addAttribute("questionTypes", enums);
			model.addAttribute("quizId", id);
			return "quiz-update-question";
		} catch (Exception e) {
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
			return "quiz-update-question";

		} else {
			System.out.println("no proble");
			quizService.updateQuizQuestionById(quizQuestion,questionId);
			return "redirect:/quiz/{id}";
		}
	}
	
	
	@GetMapping("/quiz/{id}/deleteQuestion/{questionId}")
	public String getDeleteEmployeeById(Model model, 
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
			Boolean[] list = {true, false};
			model.addAttribute("boolList",  list);
			model.addAttribute("quizId", id);
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
		if (result.hasErrors()) { 
			System.out.println(result); 
			return "quiz-question-add-answers";

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
			Boolean[] list = {true, false};
			model.addAttribute("boolList",  list);
			model.addAttribute("quizAnswer", quizService.getQuizAnswerById(answerId));
			model.addAttribute("quizId", id);
			return "quiz-answer-update";
		} catch (Exception e) {
			System.out.println(e);
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
				throw new Exception("can't update");
			}
		} else {
			return "quiz-answer-update";
		}
	}
}
