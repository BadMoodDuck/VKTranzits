package lv.venta.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lv.venta.demo.services.IQuizService;

@Controller
public class QuizController {

	@Autowired
	private IQuizService quizService;
	
	@GetMapping("/quiz/{id}") // All Courses
	public String getOneCourse(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("quiz", quizService.getQuizById(id));
		return "quiz-one";
	}
}
