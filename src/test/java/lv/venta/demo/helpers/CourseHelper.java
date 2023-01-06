package lv.venta.demo.helpers;

import lv.venta.demo.models.Course;

public class CourseHelper {
	
	public CourseHelper() {
		
	}

	public static Course createCourse(String title, String desc) {
		Course course = new Course();
		course.setTitle(title);
		course.setDescription(desc);
		return course;
	}
	
}
