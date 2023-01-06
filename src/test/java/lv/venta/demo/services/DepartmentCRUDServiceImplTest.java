package lv.venta.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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

import lv.venta.demo.helpers.DepartmentHelper;
import lv.venta.demo.models.Course;
import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
import lv.venta.demo.repos.ICourseRepo;
import lv.venta.demo.repos.IDepartmentRepo;
import lv.venta.demo.repos.IEmployeeRepo;
import lv.venta.demo.services.impl.DepartmentCRUDServiceImpl;

@ExtendWith(SpringExtension.class)
public class DepartmentCRUDServiceImplTest {
	
	private DepartmentCRUDServiceImpl departmentServiceImpl;
	
	@Mock
	private IDepartmentRepo departmentRepo;
	
	@Mock
	private IEmployeeRepo emRepo;
	
	@Mock
	private ICourseRepo courseRepo;
	
	@BeforeEach
	void beforeEach() {
		departmentServiceImpl = new DepartmentCRUDServiceImpl(departmentRepo, emRepo, courseRepo);
	}
	
	@AfterEach
	void afterEach() {
		reset(departmentRepo, emRepo, courseRepo);
	}
	
	@Test
	void getAllDepartmentsTest() {
		Course course = createCourse("Test", "Test");
		
		Collection<Course> courses = new ArrayList<>();
		courses.add(course);
		
		Department department = DepartmentHelper.createDepartment("Department", courses);
		
		ArrayList<Department> savedDepartments = new ArrayList<>();
		savedDepartments.add(department);
		
		when(departmentRepo.findAll()).thenReturn(savedDepartments);
		
		ArrayList<Department> result = departmentServiceImpl.getAllDepartments();
		
		assertEquals(1, result.size());
	}
	
	@Test
	void getAllCoursesFromDepartmentTest() {
		Course course = createCourse("Test", "Test");
		
		Collection<Course> courses = new ArrayList<>();
		courses.add(course);
		
		Department department = DepartmentHelper.createDepartment("Department", courses);
		
		when(departmentRepo.existsById(department.getIdDe())).thenReturn(true);
		when(departmentRepo.findById(department.getIdDe())).thenReturn(Optional.of(department));
		
		departmentServiceImpl.getAllCoursesFromDepartment(department.getIdDe());
		
		verify(courseRepo, times(1)).findByDepartments(department);
	}
	
	@Test
	void deleteDepartmentByIdTest() {
		Course course = createCourse("Test", "Test");
		
		Collection<Course> courses = new ArrayList<>();
		courses.add(course);
		
		Department department = DepartmentHelper.createDepartment("Department", courses);
		Employee employee = createEmployee(department);
		
		ArrayList<Employee> employees = new ArrayList<>();
		employees.add(employee);
		
		ArrayList<Course> arrayCourses = new ArrayList<>();
		arrayCourses.add(course);
		
		when(departmentRepo.existsById(department.getIdDe())).thenReturn(true);
		when(emRepo.findByDepartmentIdDe(department.getIdDe())).thenReturn(employees);
		when(courseRepo.findByDepartmentsIdDe(department.getIdDe())).thenReturn(arrayCourses);
		
		departmentServiceImpl.deleteDepartmentById(department.getIdDe());
		
		verify(emRepo, times(1)).save(any());
		verify(courseRepo, times(1)).save(any());
		verify(departmentRepo, times(1)).deleteById(department.getIdDe());
	}
	
	@Test
	void getAllEmployeesFromDepartmentTest() {
		Course course = createCourse("Test", "Test");
		
		Collection<Course> courses = new ArrayList<>();
		courses.add(course);
		
		Department department = DepartmentHelper.createDepartment("Department", courses);
		
		Employee employee = createEmployee(department);
		
		ArrayList<Employee> employees = new ArrayList<>();
		employees.add(employee);
		
		when(departmentRepo.existsById(department.getIdDe())).thenReturn(true);
		when(emRepo.findByDepartmentIdDe(department.getIdDe())).thenReturn(employees);
		
		departmentServiceImpl.getAllEmployeesFromDepartment(department.getIdDe());
		
		verify(emRepo, times(1)).findByDepartmentIdDe(department.getIdDe());
	}
	
	@Test
	void insertNewDepartmentTest() {
		Course course = createCourse("Test", "Test");
		
		Collection<Course> courses = new ArrayList<>();
		courses.add(course);
		
		Department department = DepartmentHelper.createDepartment("Department", courses);
		
		when(departmentRepo.existsByName(department.getName())).thenReturn(false);
		
		departmentServiceImpl.insertNewDepartment(department);
		
		verify(departmentRepo, times(1)).save(department);
	}
	
	@Test
	void readDepartmentByIdTest() throws Exception {
		Course course = createCourse("Test", "Test");
		
		Collection<Course> courses = new ArrayList<>();
		courses.add(course);
		
		Department department = DepartmentHelper.createDepartment("Department", courses);
		
		when(departmentRepo.existsById(department.getIdDe())).thenReturn(true);
		when(departmentRepo.findByIdDe(department.getIdDe())).thenReturn(department);
		
		Department result = departmentServiceImpl.readDepartmentById(department.getIdDe());
		
		assertEquals(department, result);
	}
	
	@Test
	void updateDepartmentByIdTest() {
		Course course = createCourse("Test", "Test");
		
		Collection<Course> courses = new ArrayList<>();
		courses.add(course);
		
		Department department = DepartmentHelper.createDepartment("Department", courses);
		
		when(departmentRepo.existsById(department.getIdDe())).thenReturn(true);
		when(departmentRepo.findById(department.getIdDe())).thenReturn(Optional.of(department));
		
		departmentServiceImpl.updateDepartmentById(department.getIdDe(), department);
		
		verify(departmentRepo,times(1)).save(department);
	}
	
	
	private Course createCourse(String title, String desc) {
		Course course = new Course();
		course.setTitle(title);
		course.setDescription(desc);
		return course;
	}
	
	private Employee createEmployee(Department dep) {
		Employee employee = new Employee();
		employee.setDepartment(dep);
		return employee;
	}
}
