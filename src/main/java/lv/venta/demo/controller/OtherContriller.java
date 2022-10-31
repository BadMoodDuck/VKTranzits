package lv.venta.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.demo.services.IDepartmentCRUDService;

@Controller
public class OtherContriller {
	
	@Autowired
	IDepartmentCRUDService departmentService;
	
	@GetMapping("/department") // All Employees
	public String getAllEmployees(Model model) {
		model.addAttribute("departments", departmentService.getAllDepartments());
		return "department-all";
	}
}
