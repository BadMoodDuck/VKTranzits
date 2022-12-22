package lv.venta.demo.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.Employee;
import lv.venta.demo.msg.MQConfig;
import lv.venta.demo.msg.MyMessage;

import lv.venta.demo.services.IDepartmentCRUDService;
import lv.venta.demo.services.IEmployeeCRUDservice;
import lv.venta.demo.services.ICompanyService;
import lv.venta.demo.services.IPositionService;

@Controller
public class EmployeeController {

	@Autowired
	private IEmployeeCRUDservice employeeService;

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private IDepartmentCRUDService departmentsService;

	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IPositionService positionService;

	@GetMapping("/employees") // All Employees
	public String getAllEmployees(Model model) {
		model.addAttribute("employee", employeeService.selectAllEmployees());
		return getPageEmployees(model, 1);
	}

	@GetMapping("/employees/{pageNr}") // All Employees
	public String getPageEmployees(Model model, @PathVariable("pageNr") int currentPage) {
		Page<Employee> page = employeeService.getPageList(currentPage);
		model.addAttribute("employee", page);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		return "employee-all";
	}

	@GetMapping("/employees/{pageNr}/{field}") // Sort All Employees //TODO README: Sorting works but it sorts all of
												// the elements even the ones not displayed so switching pages will be
												// confusing
	public String getAllEmployeesWithSort(Model model, @PathVariable("pageNr") int currentPage,
			@PathVariable("field") String field, @PathParam("sortDir") String sortDir) {

		Page<Employee> page = employeeService.getPageListWithSort(currentPage, field, sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("employee", page);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("field", field);
		return "employee-all";
	}

	@GetMapping("/employee/addNew") // Add Employee
	public String getAddEmployee(Model model, Employee employee) {
		model.addAttribute("departments", departmentsService.getAllDepartments());
		model.addAttribute("positions", positionService.getAllPositions());
		return "employee-add";
	}

	@PostMapping("/employee/addNew")
	public String postAddEmployee(@Valid Employee employee, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result);
			return "employee-add";
		} else {
			employeeService.insertNewEmployee(employee);
			return "redirect:/employees";
		}
	}

	@GetMapping("/employee/{id}")
	public String getOneEmployee(Model model, @PathVariable(name = "id") int id) {
		try {
			model.addAttribute("employee", employeeService.readEmployeeById(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "employee-one";
	}

	// localhost:8080/employee/delete/{id}
	@GetMapping("/employee/delete/{id}")
	public String getDeleteEmployeeById(Model model, @PathVariable(name = "id") int id) {
		// MyMessage message = new MyMessage("Employee deleted successfully");
		// template.convertAndSend(MQConfig.exchange, MQConfig.routingKey, message);
		model.addAttribute("Employee", employeeService.deleteEmployeeById(id));
		return "redirect:/employees";
	}

	// TODO fix update
	// localhost:8080/employee/update/{id}
	@GetMapping("/employee/update/{id}")
	public String getUpdateEmployeeById(@PathVariable(name = "id") int id, Model model) throws Exception {
		try {
			model.addAttribute("employee", employeeService.readEmployeeById(id));
			model.addAttribute("departments", departmentsService.getAllDepartments());
			model.addAttribute("positions", positionService.getAllPositions());
			return "employee-update";
		} catch (Exception e) {
			throw new Exception("can't find employee");
		}
	}

	// localhost:8080/course/update/{id}
	@PostMapping("/employee/update/{id}")
	public String postUpdateEmployeeById(@PathVariable(name = "id") int id, Employee employee, BindingResult result)
			throws Exception {
		if (!result.hasErrors()) {
			if (employeeService.updateEmployeeById(id, employee)) {
				return "redirect:/employees";
			} else {
				throw new Exception("can't update");
			}
		} else {
			return "employee-update";
		}
	}

}
