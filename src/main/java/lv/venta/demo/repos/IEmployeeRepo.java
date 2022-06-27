package lv.venta.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Employee;

public interface IEmployeeRepo extends CrudRepository<Employee, Integer> {

	Employee findByIdEm(int employeeId);

	ArrayList<Employee> findAllByDepartmentIdDe(int departmentId);

	boolean existsByDepartmentIdDe(int departmentId);

	boolean existsByIdEm(int employeeId);

	void deleteByIdEm(int employeeId);

	boolean existsByEmailOrPhone(String email, int phone);

	Employee findByEmailOrPhone(String email, int phone);

}
