package lv.venta.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import lv.venta.demo.models.Course;

public interface ICourseRepo extends PagingAndSortingRepository<Course, Integer> {
	
	Course findByIdCou(int courseId);

	boolean existsByTitleIgnoreCase(String title);

	ArrayList<Course> findByDepartmentsIdDe(int id);

}
