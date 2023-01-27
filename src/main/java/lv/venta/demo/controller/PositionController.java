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

import lv.venta.demo.models.Employee;
import lv.venta.demo.models.Position;
import lv.venta.demo.services.IPositionService;

@Controller
public class PositionController {
	
	@Autowired
	private IPositionService positionService;
	
	
		
		@GetMapping("/positions") // All Positions
		public String getAllPositions(Model model) {
			return getPagePositions(model, 1);
		}
		
		@GetMapping("/positions/{pageNr}") 
		public String getPagePositions(Model model, @PathVariable("pageNr") int currentPage) {
			Page<Position> page =positionService.getPageList(currentPage);
			model.addAttribute("position", page);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("totalElements", page.getTotalElements());
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("positions", page.getContent());
			return "position/position-all";
		}

		
		@GetMapping("/position/addNew") 
		public String getAddPosition(Position position) {
			return "position/position-add";
		}
		
		@PostMapping("/position/addNew") 
		public String postAddPosition(@Valid Position position, BindingResult result) {
			if (result.hasErrors()) {
				System.out.println(result);
				return "error";
			} else {
				positionService.insertNewPosition(position);
				return "redirect:/positions";
			}
		}
		
		@Transactional
		@GetMapping("/position/delete/{id}")
		public String getDeletePositionById(@PathVariable(name = "id") int id) {
			positionService.deletePositionById(id);
			return "redirect:/positions";
		}
		
		@GetMapping("/position/update/{id}")
		public String getUpdatePositionById(@PathVariable(name = "id") int id, Model model) throws Exception {
			try {
				model.addAttribute("position", positionService.readPositionById(id));
				return "position/position-update";
			} catch (Exception e) {
				throw new Exception("can't find position");
			}
		}
		
		@PostMapping("/position/update/{id}")
		public String postUpdatePositionById(@PathVariable(name = "id") int id, Position position, BindingResult result)
				throws Exception {
			if (!result.hasErrors()) {
				if (positionService.updatePositionById(id, position)) {
					return "redirect:/positions";
				} else {
					throw new Exception("can't update");
				}
			} else {
				return "position/position-update";
			}
			
		}


}
