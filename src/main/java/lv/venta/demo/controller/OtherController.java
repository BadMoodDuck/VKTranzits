package lv.venta.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.demo.models.Company;
import lv.venta.demo.models.Department;
import lv.venta.demo.services.IDepartmentCRUDService;
import lv.venta.demo.services.IOtherServices;

@Controller
public class OtherController {
	
	@Autowired
	IDepartmentCRUDService departmentService;
	
	@Autowired
	IOtherServices otherService;
	
	@GetMapping("/department") // All Employees
	public String getAllEmployees(Model model) {
		model.addAttribute("departments", departmentService.getAllDepartments());
		return "department-all";
	}
	
	@GetMapping("/department/{id}") // All Employees
	public String getAllEmployeesFromDepartment(@PathVariable int id, Model model) {
		model.addAttribute("employee", departmentService.getAllEmployeesFromDepartment(id));
		return "department-employee-all";
	}
	
	@GetMapping("/department/addNew")
	public String getAddedDepartment(Model models, Department dep) {
		List<Company> listCompanies= otherService.getAllCompanies();
		models.addAttribute("listCompanies", listCompanies);
		Department depart= new Department();
		models.addAttribute("department", depart);
		return "department-add";
	}
	
	@PostMapping("/department/addNew")
	public String addDepartment(@Valid Department dep, BindingResult res/*, int id*/) {
		if(!res.hasErrors()) {
			departmentService.createNewDepartment(dep);
			
			return "redirect:/department"; 
		}else {
			return "error-add-page";
		}
		
		
		
	}
}
