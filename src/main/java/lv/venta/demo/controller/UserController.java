package lv.venta.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lv.venta.demo.models.Employee;
import lv.venta.demo.services.IEmployeeCRUDservice;

@Controller
public class UserController {

	@Autowired
	private IEmployeeCRUDservice employeeService;
	
	@GetMapping("/u/{userId}")
	public String getHomePage(Model model,
							  @PathVariable("userId") int userId) {
		try {
			Employee user = employeeService.readEmployeeById(userId);
			model.addAttribute("User",user);
			System.out.println("userhomepage");
		} catch (Exception e) {
			return "redirect:/login";
		}
		return "user-home";
	}
}
