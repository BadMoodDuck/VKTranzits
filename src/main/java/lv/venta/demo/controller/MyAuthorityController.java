package lv.venta.demo.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.demo.models.Company;
import lv.venta.demo.models.MyUserAuthority;
import lv.venta.demo.services.IMyAuthorityCRUDService;

@Controller
public class MyAuthorityController {
	
	@Autowired
	private IMyAuthorityCRUDService authorityService;
	
	@GetMapping("/authorities")
	public String getAllAuthorities(Model model) {
		model.addAttribute("authority", authorityService.selectAllAuthorities());
		return "authority/authority-all";
	}
	
	@GetMapping("/authority/addNew") 
	public String getAddAuthority(Model model) {
		model.addAttribute("authority", new MyUserAuthority());
		return "authority/authority-add";
	}

	@PostMapping("/authority/addNew") 
	public String postAddAuthority(@Valid MyUserAuthority authority, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			System.out.println(result);
			throw new Exception("Error");
		} else {
			authorityService.insertNewAuthority(authority);
			return "redirect:/authorities";
		}
	}
	
	@Transactional
	@GetMapping("/authority/delete/{id}")
	public String getDeleteAuthorityById(@PathVariable(name = "id") int id) {
		authorityService.deleteAuthorityById(id);
		return "redirect:/authorities";
	}
	

}
