package lv.venta.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;

public interface IDepartmentRepo extends PagingAndSortingRepository<Department,Integer>{

	ArrayList<Employee> findAllByEmployeesIdEm(int employeeId);

	ArrayList<Department> findByCoursesIdCou(int courseId);

	boolean existsByName(String name);

	Department findByIdDe(int id);

	Department findByCompanyIdCo(int companyId);

	boolean existsByCompanyIdCo(int companyId);

 
}
