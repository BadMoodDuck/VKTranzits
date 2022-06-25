package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.EmployeeCourse;

public interface IEmployeeCourseRepo extends CrudRepository<EmployeeCourse, Integer> {
	
	
}
