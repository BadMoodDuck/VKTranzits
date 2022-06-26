package lv.venta.demo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.demo.models.Company;
import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
import lv.venta.demo.repos.ICompanyRepo;
import lv.venta.demo.repos.IDepartmentRepo;
import lv.venta.demo.repos.IEmployeeRepo;

@SpringBootApplication
public class VkTranzitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkTranzitsApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(IEmployeeRepo employeeRepo, IDepartmentRepo departmentRepo, 
										ICompanyRepo companyRepo)
	{
		return new CommandLineRunner() {
				
			public void run(String... args) throws Exception {
				

				Company com = new Company("Jhons");
				Company com1 = new Company("Does");
				Company com2 = new Company("Fixer");
				companyRepo.save(com);
				companyRepo.save(com1);
				companyRepo.save(com2);
				

				Department dep = new Department(com,"Plumbas");
				Department dep1 = new Department(com1,"Electricans");
				Department dep2 = new Department(com2,"Piekachus");
				departmentRepo.save(dep);
				departmentRepo.save(dep1);
				departmentRepo.save(dep2);
				
				Employee emp = new Employee("Jhon","Silver",22134570,"abce@gmail.com",dep);
				Employee emp1 = new Employee("Jhon","Silver",22564540,"sert@gmail.com",dep1);
				Employee emp2 = new Employee("Jhon","Silver",22626561,"asdf@gmail.com",dep1);
				Employee emp3 = new Employee("Jhon","Silver",22134560,"rtyh@gmail.com",dep2);
				Employee emp4 = new Employee("Jhon","Silver",22555660,"zxcv@gmail.com",dep2);
				Employee emp5 = new Employee("Lone","Man",22512310,"abece@gmail.com",dep);
				Employee emp7 = new Employee("Dude","Mane",22525250,"abolds123@gmail.com",dep1);
				employeeRepo.save(emp);
				employeeRepo.save(emp1);
				employeeRepo.save(emp2);
				employeeRepo.save(emp3);
				employeeRepo.save(emp4);
				employeeRepo.save(emp5);
				employeeRepo.save(emp7);
				
				




				
			}
		};
	}
}
