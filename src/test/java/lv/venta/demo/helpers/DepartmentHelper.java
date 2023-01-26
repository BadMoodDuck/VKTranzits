package lv.venta.demo.helpers;

import java.util.Collection;

import lv.venta.demo.models.Course;
import lv.venta.demo.models.Department;

public class DepartmentHelper {
	
	public DepartmentHelper() {
		
	}
	
	public static Department createDepartment(String name, Collection<Course> courses) {
		Department department = new Department();
		department.setName(name);
		department.setCourses(courses);
		return department;
	}

}
