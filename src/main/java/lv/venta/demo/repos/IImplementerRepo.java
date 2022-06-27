package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Implementer;

public interface IImplementerRepo extends CrudRepository<Implementer, Integer>{

	boolean existsByIdImpl(int implementerId);

	void deleteByIdImpl(int implementerId);

	boolean existsByNameIgnoreCase(String name);

	Implementer findByIdImpl(int implementerId);

}
