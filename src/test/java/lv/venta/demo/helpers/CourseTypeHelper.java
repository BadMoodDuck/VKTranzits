package lv.venta.demo.helpers;

import lv.venta.demo.models.CourseType;

public class CourseTypeHelper {
	
	public CourseTypeHelper() {
		
	}
	
	public static CourseType createCourseType(String title, String desc) {
		CourseType courseType = new CourseType();
		courseType.setDescription(desc);
		courseType.setTitle(title);
		return courseType;
	}

}
