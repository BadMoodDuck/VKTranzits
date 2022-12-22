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
import lv.venta.demo.models.Employee;
import lv.venta.demo.models.Implementer;
import lv.venta.demo.services.IImplementerCRUDservice;

@Controller
public class ImplementerController {
	
	@Autowired
	private IImplementerCRUDservice implementerService;
	
	@GetMapping("/implementers") // All implementers
	public String getAllImplementers(Model model) {
		model.addAttribute("implementers", implementerService.selectAllImplementers());
		return getPageImplementers(model, 1);
	}

	@GetMapping("/implementers/{pageNr}") // All Implementers
	public String getPageImplementers(Model model, @PathVariable("pageNr") int currentPage) {
		Page<Implementer> page = implementerService.getPageList(currentPage);
		model.addAttribute("implementer", page);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		return "implementer-all";
	}
	
	@GetMapping("/implementer/{id}")
	public String getOneImplementer(Model model, @PathVariable(name = "id") int id) {
		try {
			model.addAttribute("implementer", implementerService.readImplementerById(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "implementer-one";
	}
	
	@GetMapping("/implementer/addNew")
	public String getAddImplementer(Model model, Implementer implementer) {
		return "implementer-add";
	}

	@PostMapping("/implementer/addNew")
	public String postAddImplementer(@Valid Implementer implementer, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result);
			return "implementer-add";
		} else {
			implementerService.insertNewImplementer(implementer);
			return "redirect:/implementers";
		}
	}
	
	@GetMapping("/implementer/delete/{id}")
	public String getDeleteImplementerById(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("implementer", implementerService.deleteImplementerById(id));
		return "redirect:/implementers";
	}
	
	@GetMapping("/implementer/update/{id}") 
	public String getUpdateImplementerById(@PathVariable(name = "id") int id, Model model) throws Exception {
		try {
			model.addAttribute("implementer", implementerService.readImplementerById(id));
			return "implementer-update";
		} catch (Exception e) {
			throw new Exception("can't find");
		}
	}
	
	@PostMapping("/implementer/update/{id}") 
	public String postUpdateImplementerById(@PathVariable(name = "id") int id, Implementer implementer, BindingResult result) throws Exception {
		if (!result.hasErrors()) {
			if(implementerService.updateImplementerById(id, implementer)) {
				return "redirect:/implementers";
			} else {
				throw new Exception("can't update");
			}
		} else {
			return "implementer-update";
		}
	}

}
