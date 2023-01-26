package lv.venta.demo.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lv.venta.demo.models.MyUser;
import lv.venta.demo.models.MyUserAuthority;

public class MyUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyUser user;
	
	public MyUserDetails(MyUser user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> allAuthoritiesForUser = new ArrayList<>();

		Iterator<MyUserAuthority> iter = user.getAllAuthorities().iterator();
		while (iter.hasNext()) {
			allAuthoritiesForUser.add(new SimpleGrantedAuthority(iter.next().getTitle()));
		}

		return allAuthoritiesForUser;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}
	
	public int getEmployeeId() {
		return user.getEmployeeId();
	}
	public boolean getEmployeeExists() {
		return user.getEmployee() != null ? true : false;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
