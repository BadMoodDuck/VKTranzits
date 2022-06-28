package lv.venta.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Employee;

public interface IEmployeeRepo extends CrudRepository<Employee, Integer> {

	ArrayList<Employee> findAllByDepartmentIdDe(int departmentId);

	boolean existsByEmailOrPhone(String email, int phone);

	Employee findByEmailOrPhone(String email, int phone);

}
