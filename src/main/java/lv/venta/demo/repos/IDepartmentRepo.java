package lv.venta.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;

public interface IDepartmentRepo extends CrudRepository<Department,Integer>{

	ArrayList<Employee> findAllByEmployeesIdEm(int employeeId);

	ArrayList<Department> findByCoursesIdCou(int courseId);

 
}
