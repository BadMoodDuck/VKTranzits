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

	// TODO pabeigt funkcijas ar visam parbaudem
	@Override
	public Implementer insertNewImplementer(String name) {
		// TODO Auto-generated method stub
		Implementer result = new Implementer(name);
		if (!implementerRepo.existsByNameIgnoreCase()) {
			return result;
		}
		return null;
	}

	@Override
	public Implementer updateImplementerById(int implementerId, String name) {
		// TODO Auto-generated method stub
		if (implementerRepo.existsByIdImpl(implementerId)) {
			Implementer result = implementerRepo.findByIdImpl(implementerId);
			result = new Implementer(name);
		}
		return new Implementer(name);
	}

	@Override
	public ArrayList<Implementer> deleteImplementerById(int implementerId) {
		// TODO Auto-generated method stub
		if (implementerRepo.existsByIdImpl(implementerId)) {
			implementerRepo.deleteByIdImpl(implementerId);
		}
		ArrayList<Implementer> result = selectAllImplementers();

		return result;
	}

	@Override
	public ArrayList<Implementer> selectAllImplementers() {
		// TODO Auto-generated method stub
		ArrayList<Implementer> result = (ArrayList<Implementer>) implementerRepo.findAll();
		return result;
	}

}
