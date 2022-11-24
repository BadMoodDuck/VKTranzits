package lv.venta.demo.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import lv.venta.demo.models.CourseType;

public interface ICourseTypeRepo extends PagingAndSortingRepository<CourseType, Integer> {

}
