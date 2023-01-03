package lv.venta.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import lv.venta.demo.models.CourseCalendar;

public interface ICourseCalendarRepo extends JpaRepository<CourseCalendar, Integer> {

	
}
