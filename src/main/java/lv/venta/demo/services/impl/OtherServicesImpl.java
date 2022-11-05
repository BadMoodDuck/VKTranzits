package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Company;
import lv.venta.demo.models.Course;
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
	private ICompanyRepo companyRepo;
	
	@Override
	public ArrayList<Position> getAllPositions() {
		return (ArrayList<Position>) positionRepo.findAll();
	}

	@Override
	public ArrayList<CourseType> getAllCourseTypes() {
		return (ArrayList<CourseType>) coTypeRepo.findAll();
	}

	@Override
	public boolean insertNewCompany(Company company) {
		if(!companyRepo.existsByNameIgnoreCase(company.getName())){
			companyRepo.save(company);
			return true;
		}
		return false;
	}
	
	@Override
	public Page<Company> getPageList(int pageNr) {
		Pageable pageable = PageRequest.of(pageNr - 1, 2);
		return companyRepo.findAll(pageable);
	}

	@Override
	public Company getCompanyById(int id) throws Exception {
		if (companyRepo.existsById(id)) {
			return companyRepo.findById(id).get();
		}
		throw new Exception("Company doesn't exist");
	}

}
