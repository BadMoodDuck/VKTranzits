package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.MyUserAuthority;

public interface IMyAuthorityRepo extends CrudRepository<MyUserAuthority, Integer>{

	boolean existsByTitle(String title);

	MyUserAuthority findByIdA(int id);

}
