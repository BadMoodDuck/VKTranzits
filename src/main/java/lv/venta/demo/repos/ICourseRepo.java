package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Course;

public interface ICourseRepo extends CrudRepository<Course, Integer> {

	boolean existsByIdCou(int courseId);

	void deleteByIdCou(int courseId);

	Course findByIdCou(int courseId);

	boolean existsByTitleIgnoreCase(String title);

}
