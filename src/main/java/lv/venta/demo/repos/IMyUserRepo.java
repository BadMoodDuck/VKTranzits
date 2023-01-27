package lv.venta.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.MyUser;

public interface IMyUserRepo extends CrudRepository<MyUser, Integer>{

	MyUser findByUsername(String username);

	ArrayList<MyUser> findByAllAuthoritiesIdA(int authorityId);

}
