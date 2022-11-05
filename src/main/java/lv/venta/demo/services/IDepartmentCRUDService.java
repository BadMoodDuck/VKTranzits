package lv.venta.demo.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;

public interface IDepartmentCRUDService {
	
	ArrayList<Department> getAllDepartments();

	ArrayList<Employee> getAllEmployeesFromDepartment(int idDe);

	ArrayList<Course> getAllCoursesFromDepartment(int idDe);

	boolean insertNewDepartment(Department department);

	Page<Department> getPageList(int currentPage);
}
 