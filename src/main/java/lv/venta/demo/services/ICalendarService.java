package lv.venta.demo.services;

import java.util.Date;
import java.util.List;

import lv.venta.demo.models.CourseCalendar;

public interface ICalendarService {
	public abstract List<CourseCalendar> findAll();
}
