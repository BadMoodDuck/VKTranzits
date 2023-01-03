package lv.venta.demo.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.CourseCalendar;
import lv.venta.demo.repos.ICourseCalendarRepo;
import lv.venta.demo.services.ICalendarService;

@Service
public class CalendarServiceImpl implements ICalendarService{
	
	@Autowired
	private ICourseCalendarRepo calRepo;
	
	@Override
	public List<CourseCalendar> findAll() {
		return calRepo.findAll();
	}

}
