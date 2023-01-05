package lv.venta.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.venta.demo.models.CalendarDto;
import lv.venta.demo.models.Course;
import lv.venta.demo.models.CourseCalendar;
import lv.venta.demo.services.ICalendarService;
import lv.venta.demo.services.impl.CourseServiceImpl;

@RestController
@RequestMapping("/api/calendar/dates")
@CrossOrigin(origins = "http://localhost:3000")
public class CalendarController {
	
@Autowired
private ICalendarService calService;

@Autowired
private CourseServiceImpl courseService;

@GetMapping
public List<CalendarDto> getCalendarDates() {
    List<CourseCalendar> courseCalendar = calService.findAll();
    return courseCalendar.stream()
            .map(calendarElement -> {
                CalendarDto dto = new CalendarDto();
                dto.setStartDate(calendarElement.getStartDate());
                dto.setEndDate(calendarElement.getEndDate());
                if(calendarElement.getCourse()!=null) {
                Course course = courseService.getCourseById(calendarElement.getCourse().getIdCou());
                dto.setCourseName(course.getTitle());
                }
                return dto;
            })
            .collect(Collectors.toList());
}

//@GetMapping("/start")
//public List<Date> getCalendarsStartDates() {
//	return calService.findAll().stream().map(calendar -> calendar.getStartDate()).collect(Collectors.toList());
//}
//
//@GetMapping("/end")
//public List<Date> getCalendarsEndDates() {
//	return calService.findAll().stream().map(calendar -> calendar.getEndDate()).collect(Collectors.toList());
//}



}
