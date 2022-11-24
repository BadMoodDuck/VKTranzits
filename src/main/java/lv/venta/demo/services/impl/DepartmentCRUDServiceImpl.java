package lv.venta.demo.services.impl;

import java.util.ArrayList;
import java.util.Optional;

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
		return null;
	}


	@Override
	public Page<Department> getPageList(int currentPage) {
		Pageable pagable = PageRequest.of(currentPage-1, 10);
		return departmentRepo.findAll(pagable);
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
	
	@Override
	public boolean updateDepartmentById(int id, Department dep) {
		if(departmentRepo.existsById(id)) {
			Department depart = new Department();
			depart.setCompany(dep.getCompany());
			depart.setName(dep.getName());
			//depart.setHeadDepName(dep.getHeadDepName());
			//depart.setHeadDepSurname(dep.getHeadDepSurname());
			departmentRepo.save(depart);
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
	public boolean createNewDepartment(Department dep) {
		if(departmentRepo.existsById(dep.getIdDe())) {
			return false;
		}else {
			departmentRepo.save(dep);
			return true;
		}
		
	}

	@Override
	public boolean insertNewDepartment(Department department) {
		// TODO Auto-generated method stub
		return false;
	}

}
