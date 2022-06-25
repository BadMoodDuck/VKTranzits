package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Employee;

public interface IEmployeeRepo extends CrudRepository<Employee,Integer>{

}
