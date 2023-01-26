package lv.venta.demo.controller;

import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.demo.enums.EnumQuestionTypes;
import lv.venta.demo.models.Course;
import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
import lv.venta.demo.models.QuizEmployeeAnswer;
import lv.venta.demo.repos.IQuizEmployeeAnswer;
import lv.venta.demo.services.IEmployeeCRUDservice;
import lv.venta.demo.services.IQuizService;

@Controller
public class UserController {

	@Autowired
	private IEmployeeCRUDservice employeeService;
	@Autowired
	private IQuizService quizService;

	
	@GetMapping("/u/{userId}")
	public String getHomePage(Model model,
							  @PathVariable("userId") int userId) {
		try {
			Employee user = employeeService.readEmployeeById(userId);
			model.addAttribute("User",user);
			model.addAttribute("Quizes",quizService.getAllQuizes());
			
			System.out.println("userhomepage");
		} catch (Exception e) {
			return "redirect:/login";
		}
		return "user-home";
	}
	
	@GetMapping("/u/{userId}/quiz/{quizId}/{question}")
	public String getQuizForm(Model model,
							  @PathVariable(name="userId") int userId,
							  @PathVariable(name="quizId") int quizId,
							  @PathVariable(name="question") int question,
							  QuizEmployeeAnswer quizEmployeeAnswer) {
		try {
			model.addAttribute("Question", quizService.getQuizQuestionByQuizIdAndParam(quizId,question));
			model.addAttribute("QuestionSize", quizService.getQuestionSizeByQuizId(quizId));
			model.addAttribute("CurrentQuestion", question);
			model.addAttribute("Quiz", quizService.getQuizById(quizId));
			model.addAttribute("EnumQuestionTypes", EnumQuestionTypes.values());
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/u/{userId}";
		}
		return "quiz-employee-answer-form";
	}
	@PostMapping("/u/{userId}/quiz/{quizId}/{question}")
	public String postQuizForm(@PathVariable(name="userId") int userId,
							   @PathVariable(name="quizId") int quizId,
							   @PathVariable(name="question") int question,
							   QuizEmployeeAnswer quizEmployeeAnswer, 
							   BindingResult result) {
		try {
			quizService.insertNewQuizEmployeeAnswer(userId,quizId,question,quizEmployeeAnswer);
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/u/{userId}";
		}
		if (question < quizService.getQuestionSizeByQuizId(quizId)) 
			return "redirect:/u/{userId}/quiz/{quizId}/"+(question+1)+"";
		else
			return "redirect:/u/{userId}";
	}
}
