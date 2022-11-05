package lv.venta.demo.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import lv.venta.demo.models.Company;
import lv.venta.demo.models.Course;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.Position;

public interface IOtherServices {

	ArrayList<Position> getAllPositions();
	
	ArrayList<CourseType> getAllCourseTypes();
	
	boolean insertNewCompany(Company company);

	Page<Company> getPageList(int pageNr);

	Company getCompanyById(int id) throws Exception;

	ArrayList<Company> getAllCompanies();
}
