package lv.venta.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.demo.models.Company;
import lv.venta.demo.models.Course;
import lv.venta.demo.models.Department;
import lv.venta.demo.services.IDepartmentCRUDService;
import lv.venta.demo.services.IEmployeeCRUDservice;
import lv.venta.demo.services.IOtherServices;

@Controller
public class OtherController {
	
	@Autowired
	private IDepartmentCRUDService departmentService;
	
	@Autowired
	private IOtherServices otherService;
	
	@GetMapping("/departments") // All Employees
	public String getAllEmployees(Model model) {
		model.addAttribute("departments", departmentService.getAllDepartments());
		return "department-all";
	}
	
	@GetMapping("/department/{id}/employees") // All Employees of department
	public String getAllEmployeesFromDepartment(@PathVariable int id, Model model) {
		model.addAttribute("employee", departmentService.getAllEmployeesFromDepartment(id));
		return "department-employee-all";
	}
	
	@GetMapping("/department/{id}/courses") // All Courses of department
	public String getAllCoursesFromDepartment(@PathVariable int id, Model model) {
		model.addAttribute("coruses", departmentService.getAllCoursesFromDepartment(id));
		return "department-course-all";
	}
	
	@GetMapping("/department/addNew") // TODO NEEDS Company	
	public String getAdddepartment(Model model, Department department) {
		model.addAttribute("companies", otherService.getAllCompanies());
		return "department-add";
	}

	@PostMapping("/department/addNew") 
	public String postAdddepartment(@Valid Department department, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "error";
		} else {
			departmentService.insertNewDepartment(department);
			return "redirect:/departments";
		}
	}
	
	@GetMapping("/companies") // All Companies
	public String getAllCompanies(Model model) {
		return getPageCompanies(model, 1);
	}
	
	@GetMapping("/companies/{pageNr}") 
	public String getPageCompanies(Model model, @PathVariable("pageNr") int currentPage) {
		Page<Company> page = otherService.getPageList(currentPage);
		model.addAttribute("company", page);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		return "company-all";
	}
	
	@GetMapping("/company/{id}")
	public String getOneCompany(Model model, @PathVariable(name = "id") int id) {
		try {
			model.addAttribute("company", otherService.getCompanyById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "company-one";
	}
	
	@GetMapping("/company/addNew") 
	public String getAddCompany(Company company) {
		return "company-add";
	}

	@PostMapping("/company/addNew") 
	public String postAddCompany(@Valid Company company, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "error";
		} else {
			otherService.insertNewCompany(company);
			return "redirect:/companies";
		}
	}
	

}