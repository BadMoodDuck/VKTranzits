package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.CourseCalendar;

public interface ICourseCalendarRepo extends CrudRepository<CourseCalendar, Integer> {

}
