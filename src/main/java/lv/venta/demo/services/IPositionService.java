package lv.venta.demo.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import lv.venta.demo.models.Position;

public interface IPositionService {
	
	ArrayList<Position> getAllPositions();

	boolean insertNewPosition(Position position);

	Page<Position> getPageList(int pageNr);

	boolean deletePositionById(int positionId);

}
