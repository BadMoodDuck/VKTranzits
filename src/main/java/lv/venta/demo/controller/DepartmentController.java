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

import lv.venta.demo.models.Department;
import lv.venta.demo.services.IDepartmentCRUDService;
import lv.venta.demo.services.ICompanyService;

@Controller
public class DepartmentController {
	
	@Autowired
	private IDepartmentCRUDService departmentService;
	
	@Autowired
	private ICompanyService companyService;
	
	@GetMapping("/departments") // All Employees
	public String getAllDepartments(Model model) {
		model.addAttribute("departments", departmentService.getAllDepartments());
		return getPageDepartments(model, 1);
	}
	@GetMapping("/departments/{pageNr}") // All Employees
	public String getPageDepartments(Model model, @PathVariable("pageNr") int currentPage) {
		Page<Department> page = departmentService.getPageList(currentPage);
		model.addAttribute("departments", page);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
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
		model.addAttribute("companies", companyService.getAllCompanies());
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
	
	@GetMapping("/department/update/{id}") 
	public String getUpdateDepartmentById(@PathVariable(name = "id") int id, Model model) throws Exception {
		try {
			model.addAttribute("department", departmentService.readDepartmentById(id));
			return "department-update";
		} catch (Exception e) {
			throw new Exception("can't find department");
		}
	}
	
	@PostMapping("/department/update/{id}") 
	public String postUpdateDepartmentById(@PathVariable(name = "id") int id, Department department, BindingResult result) throws Exception {
		if (!result.hasErrors()) {
			if(departmentService.updateDepartmentById(id, department)) {
				return "redirect:/departments";
			} else {
				throw new Exception("can't update");
			}
		} else {
			return "department-update";
		}
	}

}
