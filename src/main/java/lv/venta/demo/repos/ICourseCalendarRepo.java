package lv.venta.demo.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.CourseCalendar;

public interface ICourseCalendarRepo extends JpaRepository<CourseCalendar, Integer> {

	
}
