package lv.venta.demo.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Position;

public interface IPositionRepo extends CrudRepository<Position, Integer> {

	boolean existsByTitleIgnoreCase(String title);

	Page<Position> findAll(Pageable pageable);

	Position findByIdPos(int id);

}
