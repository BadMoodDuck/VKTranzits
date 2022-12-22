package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Employee;
import lv.venta.demo.models.Implementer;
import lv.venta.demo.repos.IImplementerRepo;
import lv.venta.demo.services.IImplementerCRUDservice;

@Service
public class ImplementerCRUDserviceImpl implements IImplementerCRUDservice {

	@Autowired
	private IImplementerRepo implementerRepo;

	public boolean insertNewImplementer(Implementer implementer) {
		if (implementerRepo.existsByName(implementer.getName())) {
			return false;
		}
		implementerRepo.save(implementer);
		return true;
	}

	@Override
	public boolean updateImplementerById(int implementerId, Implementer implementer) {
		Implementer result = new Implementer();
		if (implementerRepo.existsById(implementerId)) {
			result = implementerRepo.findById(implementerId).get();
			result.setName(implementer.getName());
			implementerRepo.save(result);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteImplementerById(int implementerId) {
		if (implementerRepo.existsById(implementerId)) 
		{
			implementerRepo.deleteById(implementerId);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Implementer> selectAllImplementers() {
		return (ArrayList<Implementer>) implementerRepo.findAll();
	}
	
	@Override
	public Page<Implementer> getPageList(int pageNr) {
		Pageable pageable = PageRequest.of(pageNr-1, 10);
		return implementerRepo.findAll(pageable);
	}
	
	@Override
	public Implementer readImplementerById(int id) throws Exception {
		if(implementerRepo.existsById(id))
		{
			Implementer impl = implementerRepo.findByIdImpl(id);
			return impl;
		}
		throw new Exception("Implementer doesn't exist");
	}

}
