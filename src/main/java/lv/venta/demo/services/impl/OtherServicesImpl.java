package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Company;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.Position;
import lv.venta.demo.repos.ICompanyRepo;
import lv.venta.demo.repos.ICourseTypeRepo;
import lv.venta.demo.repos.IPositionRepo;
import lv.venta.demo.services.IOtherServices;

@Service
public class OtherServicesImpl implements IOtherServices {

	@Autowired
	private IPositionRepo positionRepo;
	
	@Autowired
	private ICourseTypeRepo coTypeRepo;
	
	@Autowired
	private ICompanyRepo compRepo;
	
	@Override
	public ArrayList<Position> getAllPositions() {
		return (ArrayList<Position>) positionRepo.findAll();
	}

	@Override
	public ArrayList<CourseType> getAllCourseTypes() {
		return (ArrayList<CourseType>) coTypeRepo.findAll();
	}

	@Override
	public ArrayList<Company> getAllCompanies() {
		return (ArrayList<Company>) compRepo.findAll();
	}

}
