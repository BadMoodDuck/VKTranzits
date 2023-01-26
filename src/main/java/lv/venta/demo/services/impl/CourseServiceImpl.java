package lv.venta.demo.services.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.Department;
import lv.venta.demo.models.Quiz;
import lv.venta.demo.repos.ICourseRepo;
import lv.venta.demo.repos.ICourseTypeRepo;
import lv.venta.demo.repos.IDepartmentRepo;
import lv.venta.demo.services.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private ICourseRepo courseRepo;
	@Autowired
	private IDepartmentRepo departmentRepo;
	@Autowired 
	private ICourseTypeRepo coTypeRepo;
	
	public CourseServiceImpl(
			ICourseRepo courseRepo,
			IDepartmentRepo departmentRepo,
			ICourseTypeRepo coTypeRepo
			) {
		this.courseRepo = courseRepo;
		this.departmentRepo = departmentRepo;
		this.coTypeRepo = coTypeRepo;
	}
	
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
		if (!courseRepo.existsByTitleIgnoreCase(course.getTitle())) {
			courseRepo.save(course);
		}
		return true;
	}

	@Override
	public boolean updateExistingCourseById(int courseId, Course course) {
		
		Course result = new Course();
		if (courseRepo.existsById(courseId)) {
			result = courseRepo.findById(courseId).get();
			result.setCoType(course.getCoType());
			result.setTitle(course.getTitle());
			result.setDescription(course.getDescription());
			courseRepo.save(result);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Course readCourseById(int id) throws Exception {
		
		if(courseRepo.existsById(id))
		{
			Course course = courseRepo.findByIdCou(id);
			return course;
		}
		
		throw new Exception("Course doesn't exist");
		
	}

	
	@Override
	public boolean deleteCourseById(int courseId) {
		if (courseRepo.existsById(courseId)) {
			Course course = courseRepo.findById(courseId).get();
			
			ArrayList<Department> allDepForThisCourse = departmentRepo.findByCoursesIdCou(courseId);
			for (Department department : allDepForThisCourse) {
				department.removeCourse(course);
				departmentRepo.save(department);
			}

			courseRepo.deleteById(courseId);
			return true;
		}
		return false;
	}
 
	@Override
	public Page<Course> getPageList(int pageNr) {
		Pageable pageable = PageRequest.of(pageNr - 1, 10);
		return courseRepo.findAll(pageable);
	}
	

	@Override
	public Page<Course> getPageListWithSort(int pageNr, String field, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
				Sort.by(field).ascending(): Sort.by(field).descending();
		
		Pageable pageable = PageRequest.of(pageNr - 1, 10, sort);
		return courseRepo.findAll(pageable);
	
	}

	@Override
	public Page<CourseType> getPageListWithSortCourseType(int pageNr) {
		Pageable pageable = PageRequest.of(pageNr-1, 10);
		return coTypeRepo.findAll(pageable);
	}

	@Override
	public boolean insertNewCourseType(CourseType courseType) {
		coTypeRepo.save(courseType);
		return true;
	}

	@Override
	public boolean updateExistingCourseTypeById(int courseTypeId, CourseType courseType) {
		CourseType result = new CourseType();
		if (coTypeRepo.existsById(courseTypeId)) {
			result = coTypeRepo.findById(courseTypeId).get();
			result.setCourses(courseType.getCourses());
			result.setTitle(courseType.getTitle());
			result.setIsObligatory(courseType.getIsObligatory());
			result.setDescription(courseType.getDescription());
			coTypeRepo.save(result);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public CourseType getCourseTypeById(int courseTypeId) {
		if (coTypeRepo.existsById(courseTypeId)) {
			return coTypeRepo.findById(courseTypeId).get();
		}
		return null;
	}

	@Override
	public boolean deleteCourseTypeById(int courseTypeId) {
		try {
			
			ArrayList<Course> courseList = courseRepo.findAllByCoTypeIdTy(courseTypeId);
			for (Course course : courseList) {
				course.removeCourseType();
			}
			coTypeRepo.deleteById(courseTypeId);
			return true;
		}catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	public ArrayList<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return (ArrayList<Course>) courseRepo.findAll();
	}
	
}
