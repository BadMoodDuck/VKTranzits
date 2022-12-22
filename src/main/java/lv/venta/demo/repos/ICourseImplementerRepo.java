package lv.venta.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.CourseImplementer;

public interface ICourseImplementerRepo extends CrudRepository<CourseImplementer, Integer>{

	ArrayList<CourseImplementer> findByImplementerIdImpl(int implementerId);

}
