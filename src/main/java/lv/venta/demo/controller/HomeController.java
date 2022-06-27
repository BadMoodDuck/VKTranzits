package lv.venta.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	
	@GetMapping("")
	public String login() {
		System.out.println("login");
		return "login-form";
	}
	
	@GetMapping("/home")
	public String getHomePage() {
		System.out.println("homepage");
		return "home";
	}
	
	@GetMapping("/employees")
	public String getAllEmployees(Model model) {
		
		System.out.println("employees");
		return "employees";
	}
}
