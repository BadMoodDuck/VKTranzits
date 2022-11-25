package lv.venta.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.Department;

public interface ICourseRepo extends PagingAndSortingRepository<Course, Integer> {
	
	Course findByIdCou(int courseId);

	boolean existsByTitleIgnoreCase(String title);

	ArrayList<Course> findByDepartments(Department department);

	ArrayList<Course> findAllByCoTypeIdTy(int coTypeId);

}
