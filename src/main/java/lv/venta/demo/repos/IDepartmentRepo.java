package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Department;

public interface IDepartmentRepo extends CrudRepository<Department,Integer>{

}
