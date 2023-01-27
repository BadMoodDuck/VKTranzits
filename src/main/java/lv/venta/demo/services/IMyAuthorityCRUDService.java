package lv.venta.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.MyUserAuthority;

public interface IMyAuthorityCRUDService {

	boolean insertNewAuthority(MyUserAuthority authority);

	ArrayList<MyUserAuthority> selectAllAuthorities();

}
