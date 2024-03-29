package lv.venta.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Company;
import lv.venta.demo.models.Department;
import lv.venta.demo.repos.ICompanyRepo;
import lv.venta.demo.repos.IDepartmentRepo;
import lv.venta.demo.services.ICompanyService;

@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyRepo compRepo;

	@Autowired
	private IDepartmentRepo departmentRepo;

	public CompanyServiceImpl(ICompanyRepo compRepo) {
		this.compRepo = compRepo;
	}

	@Override
	public boolean insertNewCompany(Company company) {
		if (!compRepo.existsByNameIgnoreCase(company.getName())) {
			compRepo.save(company);
			return true;
		}
		return false;
	}

	@Override
	public Page<Company> getPageList(int pageNr) {
		Pageable pageable = PageRequest.of(pageNr - 1, 10);
		return compRepo.findAll(pageable);
	}

	@Override
	public Company getCompanyById(int id) throws Exception {
		if (compRepo.existsById(id)) {
			return compRepo.findById(id).get();
		}
		throw new Exception("Company doesn't exist");
	}

	@Override
	public ArrayList<Company> getAllCompanies() {
		return (ArrayList<Company>) compRepo.findAll();
	}

	@Override
	public boolean updateExistingCompanyById(int companyId, Company company) {

		Company result = new Company();
		if (compRepo.existsById(companyId)) {
			result = compRepo.findById(companyId).get();
			result.setName(company.getName());
			compRepo.save(result);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Company readCompanyById(int id) throws Exception {
		// TODO Auto-generated method stub
		if (compRepo.existsById(id)) {
			Company company = compRepo.findByIdCo(id);
			return company;
		}

		throw new Exception("Company doesn't exist");

	}

	@Override
	public boolean deleteCompanyById(int companyId) {
		if (compRepo.existsById(companyId)) {

			ArrayList<Department> depForThisCompany = departmentRepo.findByCompanyIdCo(companyId);
			for (Department department : depForThisCompany) {
				department.removeCompany();
				departmentRepo.save(department);
			}
			compRepo.deleteById(companyId);
			return true;
		}
		return false;
	}
}
