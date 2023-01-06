package lv.venta.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lv.venta.demo.helpers.CourseTypeHelper;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.repos.ICourseTypeRepo;
import lv.venta.demo.services.impl.CourseTypeServiceimpl;

@ExtendWith(SpringExtension.class)
public class CourseTypeServiceimplTest {
	
	private CourseTypeServiceimpl courseTypeServiceImpl;
	
	@Mock
	private ICourseTypeRepo coTypeRepo;
	
	@BeforeEach
	void beforeEach() {
		courseTypeServiceImpl = new CourseTypeServiceimpl(coTypeRepo);
	}
	
	@AfterEach
	void afterEach() {
		reset(coTypeRepo);
	}
	
	@Test
	void getAllCourseTypesTest() {
		CourseType courseType = CourseTypeHelper.createCourseType("Title", "Desc");
		CourseType courseType2 = CourseTypeHelper.createCourseType("Title2", "Desc2");
		
		ArrayList<CourseType> savedCourseType = new ArrayList<>();
		
		savedCourseType.add(courseType);
		savedCourseType.add(courseType2);
		
		when(coTypeRepo.findAll()).thenReturn(savedCourseType);
		
		ArrayList<CourseType> result = courseTypeServiceImpl.getAllCourseTypes();
		
		assertEquals(2, result.size());
	}

}
