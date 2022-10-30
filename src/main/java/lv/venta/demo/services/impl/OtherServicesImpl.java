package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Position;
import lv.venta.demo.repos.IPositionRepo;
import lv.venta.demo.services.IOtherServices;

@Service
public class OtherServicesImpl implements IOtherServices {

	@Autowired
	private IPositionRepo positionRepo;
	
	@Override
	public ArrayList<Position> getAllPositions() {
		return (ArrayList<Position>) positionRepo.findAll();
	}

}
