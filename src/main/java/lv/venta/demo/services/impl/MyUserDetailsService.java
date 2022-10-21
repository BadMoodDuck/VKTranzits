package lv.venta.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lv.venta.demo.config.MyUserDetails;
import lv.venta.demo.models.MyUser;
import lv.venta.demo.repos.IMyUserRepo;

public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IMyUserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MyUser user = userRepo.findByUsername(username);
		if (user != null) {
			// eksiste
			MyUserDetails details = new MyUserDetails(user);
			return details;
		} else {
			// neeksiste
			throw new UsernameNotFoundException(username + " not found");
		}
	}

}
