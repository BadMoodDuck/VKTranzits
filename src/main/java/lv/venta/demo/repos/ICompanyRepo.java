package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import lv.venta.demo.models.Company;
import lv.venta.demo.models.Course;

public interface ICompanyRepo extends PagingAndSortingRepository<Company, Integer>{

	boolean existsByNameIgnoreCase(String name);

}
