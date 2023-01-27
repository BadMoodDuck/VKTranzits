package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Implementer;
import lv.venta.demo.models.MyUserAuthority;
import lv.venta.demo.repos.IMyAuthorityRepo;
import lv.venta.demo.services.IMyAuthorityCRUDService;

@Service
public class MyAuthorityCRUDServiceImpl implements IMyAuthorityCRUDService {
	
	@Autowired
	private IMyAuthorityRepo authorityRepo;
	
	@Override
	public ArrayList<MyUserAuthority> selectAllAuthorities() {
		return (ArrayList<MyUserAuthority>) authorityRepo.findAll();
	}
	
	@Override
	public boolean insertNewAuthority(MyUserAuthority authority) {
		if(authorityRepo.existsByTitle(authority.getTitle())) {
			return false;
		} else {
			authorityRepo.save(authority);
			return true;
		}
	}

}
