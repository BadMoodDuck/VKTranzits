package lv.venta.demo.helpers;

import lv.venta.demo.models.Employee;

public class EmployeeHelper {
	
	public EmployeeHelper() {
		
	}
	
	public static Employee createEmployee() {
		Employee employee = new Employee();
		
		employee.setEmail("test@gmail.com");
		employee.setName("Steve");
		employee.setPhone(27890765);
		
		return employee;
	}

}
