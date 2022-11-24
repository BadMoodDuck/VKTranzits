package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Position;

public interface IPositionRepo extends CrudRepository<Position, Integer> {

	boolean existsByTitleIgnoreCase(String title);

}
