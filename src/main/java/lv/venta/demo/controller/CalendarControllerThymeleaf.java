package lv.venta.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController
@RequestMapping("/calendar")
public class CalendarControllerThymeleaf {
	
	@GetMapping//("/**")
	public String getCalendarView(/*HttpServletRequest request*/) {
		//String path = request.getServletPath();
		//return "forward:/"+path;
		return "calendar-view";
	}
}
