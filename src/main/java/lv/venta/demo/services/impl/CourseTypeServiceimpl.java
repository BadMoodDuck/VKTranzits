package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.CourseType;
import lv.venta.demo.repos.ICourseTypeRepo;
import lv.venta.demo.services.ICourseTypeService;

@Service
public class CourseTypeServiceimpl implements ICourseTypeService{

	@Autowired
	private ICourseTypeRepo coTypeRepo;
	
	@Override
	public ArrayList<CourseType> getAllCourseTypes() {
		return (ArrayList<CourseType>) coTypeRepo.findAll();
	}

}
