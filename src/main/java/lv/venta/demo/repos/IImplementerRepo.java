package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Implementer;

public interface IImplementerRepo extends CrudRepository<Implementer, Integer>{

	boolean existsByName(String name);

}
