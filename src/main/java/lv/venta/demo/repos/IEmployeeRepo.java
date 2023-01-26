package lv.venta.demo.repos;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import lv.venta.demo.models.Employee;

public interface IEmployeeRepo extends PagingAndSortingRepository<Employee, Integer> {
 
	Employee findByIdEm(int employeeId);

	@Query(value = "SELECT * FROM vktranzits.employee WHERE id_de=?1", nativeQuery = true)
	ArrayList<Employee> findAllByDepartmentIdDe(int departmentId);

	boolean existsByDepartmentIdDe(int departmentId);

	 
	//TODO Needs testing 
	@Query(value = "SELECT * FROM employee WHERE email=?1 OR phone=?2", nativeQuery = true)
	boolean existsByEmailOrPhone(String email, int phone);

	Employee findByEmailOrPhone(String email, int phone);

	ArrayList<Employee> findByDepartmentIdDe(int idDe);

	boolean existsByPositionIdPos(int positionId);

	ArrayList<Employee> findByPositionIdPos(int positionId);
}