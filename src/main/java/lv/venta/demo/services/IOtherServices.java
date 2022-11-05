package lv.venta.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.Company;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.Position;

public interface IOtherServices {

	ArrayList<Position> getAllPositions();
	
	ArrayList<CourseType> getAllCourseTypes();
	
	ArrayList<Company> getAllCompanies();
}
