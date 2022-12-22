package lv.venta.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import lv.venta.demo.models.Department;
import lv.venta.demo.models.EmployeeCourse;
import lv.venta.demo.models.ExcelExport;
import lv.venta.demo.services.IDepartmentCRUDService;
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
	
	@Autowired
	private IEmployeeCRUDservice eService;
	
//	@GetMapping("/department") // All Employees
//	public String getAllEmployees(Model model) {
//	private IOtherServices otherService;
	
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
	/*
	 * 	@GetMapping("/teacher/remove/{id}")
	public String deleteTeacherById(Model models, @PathVariable(name="id")int id) {
		if(teacherService.deleteTeacherById(id)) {
			models.addAttribute("object", teacherService.selectAllTeachers());
			return"teacher-all-page";
		}else {
			return "error-page";
		}
	
			
	}
	 */
	
	@GetMapping("/department/delete/{id}")
	public String deleteDepartment(Model models, @PathVariable(name="id")int id) {
		if(departmentService.deleteDepartmentById(id)) {
			models.addAttribute("departments", departmentService.getAllDepartments());
			return "redirect:/departments";
		}else {
			return "error-page";
		}
	}


	@GetMapping("/department/{id}/courses") // All Courses of department
	public String getAllCoursesFromDepartment(@PathVariable int id, Model model) {
		model.addAttribute("coruses", departmentService.getAllCoursesFromDepartment(id));
		return "department-course-all";
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
	
	@GetMapping("/export/excel")
	public void exportExcel( HttpServletResponse response) throws IOException{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename=EmployeeCourse_info.xlsx";
		response.setHeader(headerKey, headervalue);
		ArrayList<EmployeeCourse> allEmplCourse = eService.findAll();
		ExcelExport excel = new ExcelExport(allEmplCourse);
		excel.export(response);
	
	}

}