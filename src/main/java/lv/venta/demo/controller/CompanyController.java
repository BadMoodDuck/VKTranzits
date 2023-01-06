package lv.venta.demo.controller;

import javax.transaction.Transactional;
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
import lv.venta.demo.models.Employee;
import lv.venta.demo.models.Position;
import lv.venta.demo.services.IDepartmentCRUDService;
import lv.venta.demo.services.IEmployeeCRUDservice;
import lv.venta.demo.services.ICompanyService;

@Controller
public class CompanyController {
	
	@Autowired
	private ICompanyService companyService;

	
	@GetMapping("/companies") // All Companies
	public String getAllCompanies(Model model) {
		return getPageCompanies(model, 1);
	}
	
	@GetMapping("/companies/{pageNr}") 
	public String getPageCompanies(Model model, @PathVariable("pageNr") int currentPage) {
		Page<Company> page = companyService.getPageList(currentPage);
		model.addAttribute("company", page);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		return "company-all";
	}
	
	@GetMapping("/company/{id}")
	public String getOneCompany(Model model, @PathVariable(name = "id") int id) {
		try {
			model.addAttribute("company", companyService.getCompanyById(id));
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
			companyService.insertNewCompany(company);
			return "redirect:/companies";
		}
	}
	
	@GetMapping("/company/update/{id}")
	public String getUpdateCompanyById(@PathVariable(name="id") int id, Model model) throws Exception {
		try {
			model.addAttribute("company", companyService.readCompanyById(id));
			return "company-update";
		} catch (Exception e){
			throw new Exception("can't find company");
		}
		
	}

	@PostMapping("/company/update/{id}")
	public String postUpdateCourseById(@PathVariable(name = "id") int id, Company company, BindingResult result) throws Exception {
		if (!result.hasErrors()) {
			if (companyService.updateExistingCompanyById(id, company)) {
				return "redirect:/companies";
			} else {
				throw new Exception("can't update");
			}
		} else {
			return "company-update";
		}
	}
	
	@Transactional
	@GetMapping("/company/delete/{id}")
	public String getDeleteCompanyById(@PathVariable(name = "id") int id) {
		companyService.deleteCompanyById(id);
		return "redirect:/companies";
	}

}