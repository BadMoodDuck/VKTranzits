package lv.venta.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lv.venta.demo.models.Company;
import lv.venta.demo.repos.ICompanyRepo;
import lv.venta.demo.services.impl.CompanyServiceImpl;

@ExtendWith(SpringExtension.class)
public class CompanyServiceImplTest {
	
	private CompanyServiceImpl companyServiceImpl;
	
	@Mock
	private ICompanyRepo companyRepo;
	
	@BeforeEach
	void beforeEach() {
		companyServiceImpl = new CompanyServiceImpl(companyRepo);
	}
	
	@AfterEach
	void AfterEach() {
		reset(companyRepo);
	}
	
	@Test
	void insertNewCompanyTest() {
		Company company = createCompany("TestComp");
		
		Boolean result = companyServiceImpl.insertNewCompany(company);
		
		assertTrue(result);
		verify(companyRepo, times(1)).save(any());
	}
	
	@Test
	void getCompanyByIdTest() throws Exception {
			Optional<Company> company = Optional.of(createCompany("TestComp"));
			
			when(companyRepo.existsById(company.get().getIdCo())).thenReturn(true);
			
			when(companyRepo.findById(company.get().getIdCo())).thenReturn(company);
			
			Company companyResult = companyServiceImpl.getCompanyById(company.get().getIdCo());
			
			verify(companyRepo, times(1)).findById(company.get().getIdCo());
			assertNotNull(companyResult);
	}
	
	@Test
	void getCompanyByIdExceptionTest() {
			Optional<Company> company = Optional.of(createCompany("TestComp"));
			
			String errorMsg = "Company doesn't exist";
			
			when(companyRepo.existsById(company.get().getIdCo())).thenReturn(false);
			
			Throwable exception = assertThrows(Exception.class, () ->
			companyServiceImpl.getCompanyById(company.get().getIdCo())); 
			
			assertEquals(errorMsg, exception.getMessage());
	}
	
	@Test
	void getAllCompaniesTest() {
		Company company = createCompany("TestComp");
		Company company2 = createCompany("TestComp2");
				
		ArrayList<Company> savedCompanies = new ArrayList<>();
		
		savedCompanies.add(company);
		savedCompanies.add(company2);
		
		when(companyRepo.findAll()).thenReturn(savedCompanies);
		
		ArrayList<Company> result = companyServiceImpl.getAllCompanies();
		assertEquals(2, result.size());
	}
	
	private Company createCompany(String compName) {
		Company company = new Company();
		company.setName(compName);
		return company;
	}
}
