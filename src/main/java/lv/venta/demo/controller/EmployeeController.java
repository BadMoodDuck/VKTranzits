package lv.venta.demo.controller;

import javax.validation.Valid;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.demo.models.Employee;
//import lv.venta.demo.msg.MQConfig;
//import lv.venta.demo.msg.MyMessage;
import lv.venta.demo.services.IEmployeeCRUDservice;

@Controller
public class EmployeeController {

	@Autowired
	private IEmployeeCRUDservice employeeService;
	
	//@Autowired
	//private RabbitTemplate template;
	

	@GetMapping("/employee") // All Employees
	public String getAllEmployees(Model model) {
		model.addAttribute("employee", employeeService.selectAllEmployees());
		return "employee-all";
	}

	@GetMapping("/employee/addNew") // Add Employee
	public String getAddEmployee(Employee employee) {
		return "employee-add";
	}
	@PostMapping("/employee/addNew") // Papildinat ar Department 
	public String postAddEmployee(@Valid Employee employee, BindingResult result) {
		if (result.hasErrors()) { return "error"; }
		if (employeeService.insertNewEmployee(employee)) {
			return "redirect:/employee";
		}
			return "redirect:/employee";
	}

	// localhost:8080/department/{id}/showAllEmployees
	@GetMapping("/department/{id}/showAllEmployees")
	public String getAllEmployeesInDepartment(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("employee", employeeService.selectAllEmployeesFromDepartmentById(id));
		return "employee-all";
	}
	
	// localhost:8080/employee/delete/{id}
		@GetMapping("/employee/delete/{id}")
		public String getDeleteEmployeeById(Model model, @PathVariable(name = "id") int id) {
			//MyMessage message = new MyMessage("Employee deleted successfully");
			//template.convertAndSend(MQConfig.exchange, MQConfig.routingKey, message);
			model.addAttribute("Employee", employeeService.deleteEmployeeById(id));
			return "employee-all";
		}
}
