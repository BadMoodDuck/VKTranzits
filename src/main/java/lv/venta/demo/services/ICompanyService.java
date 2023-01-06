package lv.venta.demo.services;

import java.util.ArrayList;
import lv.venta.demo.models.Company;
import org.springframework.data.domain.Page;


public interface ICompanyService {

	boolean insertNewCompany(Company company);

	Page<Company> getPageList(int pageNr);

	Company getCompanyById(int id) throws Exception;
	
	ArrayList<Company> getAllCompanies();

	boolean updateExistingCompanyById(int companyId, Company company);

	Company readCompanyById(int id) throws Exception;

	boolean deleteCompanyById(int companyId);

}
