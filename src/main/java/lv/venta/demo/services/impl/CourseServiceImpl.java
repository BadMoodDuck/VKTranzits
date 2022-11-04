package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.repos.ICourseRepo;
import lv.venta.demo.services.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private ICourseRepo courseRepo;
	
	//TODO pabeigt funkcijas ar visam parbaudem
	@Override
	public Course getCourseById(int courseId) {
		if (courseRepo.existsById(courseId)) {
			return courseRepo.findById(courseId).get();
		}
		return null;
	}

	@Override
	public ArrayList<Course> selectAllCourses() {
		return (ArrayList<Course>) courseRepo.findAll();
	}

	@Override
	public boolean insertNewCourse(Course course) {
		//if (!courseRepo.existsByTitleIgnoreCase(title)) { TODO MAKE VALIDATION THAT COURSE ALREADY DOESNT EXIST
			courseRepo.save(course);
		//	return result;
		//}
		return true;
	}

	@Override
	public Course updateExistingCourseById(int courseId, CourseType coType, String title, String description) {
		// TODO Auto-generated method stub
		Course result = new Course();
		if (courseRepo.existsById(courseId)) {
			result = courseRepo.findByIdCou(courseId);
		}
		result = new Course(coType, title, description);
		return result;
	}

	
	@Override
	public boolean deleteCourseById(int courseId) {
		if (courseRepo.existsById(courseId)) {
			courseRepo.deleteById(courseId);
			return true;
		}
		return false;
	}
 
	@Override
	public Page<Course> getPageList(int pageNr) {
		Pageable pageable = PageRequest.of(pageNr - 1, 2);
		return courseRepo.findAll(pageable);
	}


}
