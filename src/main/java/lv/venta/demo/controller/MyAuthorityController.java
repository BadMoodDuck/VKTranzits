package lv.venta.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	

}
