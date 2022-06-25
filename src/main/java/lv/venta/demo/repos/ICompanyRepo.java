package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Company;

public interface ICompanyRepo extends CrudRepository<Company,Integer>{

}
