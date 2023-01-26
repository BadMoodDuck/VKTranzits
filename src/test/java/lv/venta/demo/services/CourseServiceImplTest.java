package lv.venta.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lv.venta.demo.helpers.CourseHelper;
import lv.venta.demo.helpers.CourseTypeHelper;
import lv.venta.demo.helpers.DepartmentHelper;
import lv.venta.demo.models.Course;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.Department;
import lv.venta.demo.models.Quiz;
import lv.venta.demo.repos.ICourseRepo;
import lv.venta.demo.repos.ICourseTypeRepo;
import lv.venta.demo.repos.IDepartmentRepo;
import lv.venta.demo.repos.IQuizRepo;
import lv.venta.demo.services.impl.CourseServiceImpl;

@ExtendWith(SpringExtension.class)
public class CourseServiceImplTest {
	
	private CourseServiceImpl courseServiceImpl;
	
	@Mock
	private ICourseRepo courseRepo;
	@Mock
	private IDepartmentRepo departmentRepo;
	@Mock
	private ICourseTypeRepo coTypeRepo;
	@Mock
	private IQuizRepo quizRepo;
	
	@BeforeEach
	void beforeEach() {
		courseServiceImpl = new CourseServiceImpl(courseRepo, departmentRepo, coTypeRepo, quizRepo);
	}
	
	@AfterEach
	void afterEach() {
		reset(courseRepo, departmentRepo, coTypeRepo, quizRepo);
	}
	
	@Test
	void getCourseByIdTest() {
		Course course = CourseHelper.createCourse("TestCourse", "Neobligats");
		courseRepo.save(course);
		when(courseRepo.existsById(course.getIdCou())).thenReturn(true);
		when(courseRepo.findById(anyInt())).thenReturn(Optional.of(course));
		Course result = courseServiceImpl.getCourseById(course.getIdCou());
		
		verify(courseRepo, times(1)).findById(course.getIdCou());
		assertNotNull(result);
	}
	
	@Test
	void selectAllCoursesTest() {
		Course course = CourseHelper.createCourse("Title", "Desc");
		Course course2 = CourseHelper.createCourse("TestCourse", "Test");
		
		ArrayList<Course> savedCourses = new ArrayList<>();
		
		savedCourses.add(course2);
		savedCourses.add(course);
		
		when(courseRepo.findAll()).thenReturn(savedCourses);
		
		ArrayList<Course> result = courseServiceImpl.selectAllCourses();
		
		assertEquals(2, result.size());
	}
	
	@Test
	void insertNewCourseTest() {
		Course course = CourseHelper.createCourse("Course", "Course");
		
		Boolean result = courseServiceImpl.insertNewCourse(course);
		
		assertTrue(result);
		
		verify(courseRepo, times(1)).save(any());
	}
	
	@Test
	void updateExistingCourseByIdTest() {
		Course course = CourseHelper.createCourse("Test", "Test");
		
		when(courseRepo.existsById(course.getIdCou())).thenReturn(true);
		
		when(courseRepo.findById(anyInt())).thenReturn(Optional.of(course));
		
		Boolean result = courseServiceImpl.updateExistingCourseById(course.getIdCou(), course);
		
		verify(courseRepo, times(1)).save(any());
		assertTrue(result);
	}
	
	@Test
	void updateExistingCourseByIdFailedTest() {
		Course course = CourseHelper.createCourse("Test", "Test");
		
		when(courseRepo.existsById(course.getIdCou())).thenReturn(false);
		
		Boolean result = courseServiceImpl.updateExistingCourseById(course.getIdCou(), course);
		
		verify(courseRepo, never()).save(any());
		assertFalse(result);
	}
	
	@Test
	void readCourseByIdTest() throws Exception {
		Course course = CourseHelper.createCourse("Test", "Test");
		
		when(courseRepo.existsById(course.getIdCou())).thenReturn(true);
		
		courseServiceImpl.readCourseById(course.getIdCou());
		
		verify(courseRepo, times(1)).findByIdCou(course.getIdCou());
	}
	
	@Test
	void readCourseByIdExceptionTest() {
		Course course = CourseHelper.createCourse("Test", "Test");
		String errorMsg = "Course doesn't exist";
		
		when(courseRepo.existsById(course.getIdCou())).thenReturn(false);
		
		Throwable exception = assertThrows(Exception.class, () -> 
		courseServiceImpl.readCourseById(anyInt()));
		
		verify(courseRepo, never()).findByIdCou(anyInt());
		assertEquals(errorMsg, exception.getMessage());
	}
	
	@Test
	void deleteCourseByIdTest() {
		Course course = CourseHelper.createCourse("Test", "Test");
		Collection<Course> courses = new ArrayList<>();
		courses.add(course);
		Department department = DepartmentHelper.createDepartment("Department", courses);
		
		ArrayList<Department> departments = new ArrayList<>();
		departments.add(department);
		
		ArrayList<Quiz> quizList = new ArrayList<>();
		Quiz quiz = createQuiz();
		quiz.setCourse(course);
		
		quizList.add(createQuiz());
		
		when(quizRepo.findByCourseIdCou(course.getIdCou())).thenReturn(quizList);
		when(courseRepo.existsById(anyInt())).thenReturn(true);
		when(courseRepo.findById(anyInt())).thenReturn(Optional.of(course));
		when(departmentRepo.findByCoursesIdCou(anyInt())).thenReturn(departments);
		
		courseServiceImpl.deleteCourseById(course.getIdCou());
		
		verify(departmentRepo, times(1)).save(any());
		verify(courseRepo, times(1)).deleteById(anyInt());
	}
	
	@Test
	void insertNewCourseTypeTest() {
		CourseType courseType = CourseTypeHelper.createCourseType("Title", "Desc");
		Boolean result = courseServiceImpl.insertNewCourseType(courseType);
		
		assertTrue(result);
	}
	
	@Test
	void  updateExistingCourseTypeByIdTest() {
		CourseType courseType = CourseTypeHelper.createCourseType("Title", "Desc");
		
		when(coTypeRepo.existsById(courseType.getIdTy())).thenReturn(true);
		
		when(coTypeRepo.findById(anyInt())).thenReturn(Optional.of(courseType));
	
		Boolean result = courseServiceImpl.updateExistingCourseTypeById(courseType.getIdTy(), courseType);
		
		verify(coTypeRepo, times(1)).save(any());
		assertTrue(result);
	}
	
	@Test
	void getCourseTypeByIdTest() {
		CourseType courseType = CourseTypeHelper.createCourseType("Title", "Desc");
		
		when(coTypeRepo.existsById(courseType.getIdTy())).thenReturn(true);
		when(coTypeRepo.findById(courseType.getIdTy())).thenReturn(Optional.of(courseType));
		
		courseServiceImpl.getCourseTypeById(anyInt());
		
		verify(coTypeRepo, times(1)).findById(courseType.getIdTy());
	}
	
	@Test
	void deleteCourseTypeByIdTest() {
		CourseType courseType = CourseTypeHelper.createCourseType("Title", "Desc");
	
		Collection<CourseType> coursesTypes = new ArrayList<>();
		coursesTypes.add(courseType);
		Course course = CourseHelper.createCourse("Test", "Test");
		
		ArrayList<Course> courses = new ArrayList<>();
		courses.add(course);
	
		when(courseRepo.findAllByCoTypeIdTy(anyInt())).thenReturn(courses);
		
		courseServiceImpl.deleteCourseTypeById(courseType.getIdTy());
		
		verify(coTypeRepo, times(1)).deleteById(anyInt());
	}
	
	private Quiz createQuiz() {
		Quiz quiz = new Quiz();
		quiz.setTitle("Test");
		quiz.setCourse(null);
		return quiz;
	}
}
