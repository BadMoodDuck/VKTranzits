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
	
	@GetMapping("/authority/update/{id}")
	public String getUpdateAuthorityById(@PathVariable(name="id") int id, Model model) throws Exception {
		try {
			model.addAttribute("authority", authorityService.readAuthorityById(id));
			return "authority/authority-update";
		} catch (Exception e){
			throw new Exception("can't find authority");
		}
		
	}

	@PostMapping("/authority/update/{id}")
	public String postUpdateAuthorityById(@PathVariable(name = "id") int id, MyUserAuthority authority, BindingResult result) throws Exception {
		if (!result.hasErrors()) {
			if (authorityService.updateExistingAuthorityById(id, authority)) {
				return "redirect:/authorities";
			} else {
				throw new Exception("can't update");
			}
		} else {
			return "authority/authority-update";
		}
	}

}
