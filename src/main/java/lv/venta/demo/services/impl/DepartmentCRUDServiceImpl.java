package lv.venta.demo.services.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
import lv.venta.demo.repos.ICourseRepo;
import lv.venta.demo.repos.IDepartmentRepo;
import lv.venta.demo.repos.IEmployeeRepo;
import lv.venta.demo.services.IDepartmentCRUDService;

@Service
public class DepartmentCRUDServiceImpl implements IDepartmentCRUDService{

	@Autowired
	private IDepartmentRepo departmentRepo;
	@Autowired
	private IEmployeeRepo emRepo;
	@Autowired
	private ICourseRepo courseRepo;
	
	public DepartmentCRUDServiceImpl(
			IDepartmentRepo departmentRepo,
			IEmployeeRepo emRepo,
			ICourseRepo courseRepo
			) {
		this.courseRepo = courseRepo;
		this.departmentRepo = departmentRepo;
		this.emRepo = emRepo;
	}
	
	@Override
	public ArrayList<Department> getAllDepartments() {
		return (ArrayList<Department>) departmentRepo.findAll();
	}
	
	@Override
	public ArrayList<Course> getAllCoursesFromDepartment(int idDe) {
		if (departmentRepo.existsById(idDe)) {
			Department department = departmentRepo.findById(idDe).get();
			return courseRepo.findByDepartments(department);
		}
		return new ArrayList<Course>();
	}

	@Override
	public boolean deleteDepartmentById(int id) {
		if(departmentRepo.existsById(id)) {
			ArrayList<Employee> empl = emRepo.findByDepartmentIdDe(id);
			for(Employee temp: empl) {
				temp.setDepartment(null);
				emRepo.save(temp);
			}
			ArrayList<Course> cours= courseRepo.findByDepartmentsIdDe(id);
			for(Course temp : cours) {
				temp.setDepartments(null);
				courseRepo.save(temp);
			}
			departmentRepo.deleteById(id);
			return true;
		}
		return false;
	}
	



	public ArrayList<Employee> getAllEmployeesFromDepartment(int idDe){
		if (departmentRepo.existsById(idDe)) {
			return emRepo.findByDepartmentIdDe(idDe);
			
		}
		return new ArrayList<Employee>();
	}

	@Override
	public boolean insertNewDepartment(Department department) {

		if(!departmentRepo.existsByName(department.getName())) {
		departmentRepo.save(department);
		return true;
		}
		return false;
	}

	@Override
	public Page<Department> getPageList(int currentPage) {
		Pageable pagable = PageRequest.of(currentPage-1, 10);
		return departmentRepo.findAll(pagable);
	}
	

	@Override
	public Department readDepartmentById(int id) throws Exception {
		if(departmentRepo.existsById(id)) {
			Department dep = departmentRepo.findByIdDe(id);
			return dep;
		} 
		throw new Exception("Department doesn't exist");
	}
	
	@Override
	public boolean updateDepartmentById(int departmentId, Department department) {
		Department result = new Department();
		if(departmentRepo.existsById(departmentId)) {
			result = departmentRepo.findById(departmentId).get();
			result.setName(department.getName());
			result.setCompany(department.getCompany());
			departmentRepo.save(result);
			return true;
		}
		return false;
	}

}
	
