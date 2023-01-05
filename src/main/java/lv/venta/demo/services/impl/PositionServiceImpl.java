package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Position;
import lv.venta.demo.repos.IPositionRepo;
import lv.venta.demo.services.IPositionService;

@Service
public class PositionServiceImpl implements IPositionService{
	
	@Autowired
	private IPositionRepo positionRepo;
	
	@Override
	public ArrayList<Position> getAllPositions() {
		return (ArrayList<Position>) positionRepo.findAll();
	}

	@Override
	public boolean insertNewPosition(Position position) {
		if(!positionRepo.existsByTitleIgnoreCase(position.getTitle())) {
			positionRepo.save(position);
		}
		return false;
	}
	
	@Override
	public Page<Position> getPageList(int pageNr) {
		Pageable pageable = PageRequest.of(pageNr - 1, 10);
		return positionRepo.findAll(pageable);
	
	}
}
