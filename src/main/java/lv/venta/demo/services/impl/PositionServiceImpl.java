package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Employee;
import lv.venta.demo.models.Position;
import lv.venta.demo.repos.IEmployeeRepo;
import lv.venta.demo.repos.IPositionRepo;
import lv.venta.demo.services.IPositionService;

@Service
public class PositionServiceImpl implements IPositionService {

	@Autowired
	private IPositionRepo positionRepo;

	@Autowired
	private IEmployeeRepo employeeRepo;

	@Override
	public ArrayList<Position> getAllPositions() {
		return (ArrayList<Position>) positionRepo.findAll();
	}

	@Override
	public boolean insertNewPosition(Position position) {
		if (!positionRepo.existsByTitleIgnoreCase(position.getTitle())) {
			positionRepo.save(position);
		}
		return false;
	}

	@Override
	public Page<Position> getPageList(int pageNr) {
		Pageable pageable = PageRequest.of(pageNr - 1, 10);
		return positionRepo.findAll(pageable);

	}

	@Override
	public boolean deletePositionById(int positionId) {
		if (positionRepo.existsById(positionId)) {

			ArrayList<Employee> employeeWithThisPosition = employeeRepo.findByPositionIdPos(positionId);
			for (Employee employee : employeeWithThisPosition) {
				employee.removePosition();
				employeeRepo.save(employee);
			}
			positionRepo.deleteById(positionId);
			return true;
		}
		return false;
	}
}
