package lv.venta.demo.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import lv.venta.demo.models.Employee;
import lv.venta.demo.models.Implementer;

public interface IImplementerRepo extends PagingAndSortingRepository<Implementer, Integer>{

	boolean existsByName(String name);

	Implementer findByIdImpl(int id);


}
