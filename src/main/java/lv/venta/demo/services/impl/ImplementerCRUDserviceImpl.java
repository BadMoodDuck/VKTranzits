package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Implementer;
import lv.venta.demo.repos.IImplementerRepo;
import lv.venta.demo.services.IImplementerCRUDservice;

@Service
public class ImplementerCRUDserviceImpl implements IImplementerCRUDservice {

	@Autowired
	private IImplementerRepo implementerRepo;

	@Override
	public boolean insertNewImplementer(Implementer implementer) {
		if (implementerRepo.existsById(implementer.getIdImpl())) {
			return false;
		}
		implementerRepo.save(implementer);
		return true;
	}

	@Override
	public boolean updateImplementerById(int implementerId, Implementer implementer) {
		if (implementerRepo.existsById(implementerId)) {
			Implementer result = implementerRepo.findById(implementerId).get();
			result.setName(implementer.getName());;
			implementerRepo.save(result);
		}
		return false;
	}

	@Override
	public boolean deleteImplementerById(int implementerId) {
		if (implementerRepo.existsByIdImpl(implementerId)) 
		{
			implementerRepo.deleteByIdImpl(implementerId);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Implementer> selectAllImplementers() {
		return (ArrayList<Implementer>) implementerRepo.findAll();
	}

}
