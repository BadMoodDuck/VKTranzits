package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Course;

public interface ICourseRepo extends CrudRepository<Course, Integer> {
	
}
