package lv.venta.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.MyUserAuthority;

public interface IMyAuthorityCRUDService {

	boolean insertNewAuthority(MyUserAuthority authority);

	ArrayList<MyUserAuthority> selectAllAuthorities();

	boolean deleteAuthorityById(int authorityId);

	boolean updateExistingAuthorityById(int authorityId, MyUserAuthority authority);

	MyUserAuthority readAuthorityById(int id) throws Exception;

}
