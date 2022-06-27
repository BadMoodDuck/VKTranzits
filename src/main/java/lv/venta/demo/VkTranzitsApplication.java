package lv.venta.demo;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.demo.models.Company;
import lv.venta.demo.models.Course;
import lv.venta.demo.models.CourseCalendar;
import lv.venta.demo.models.CourseImplementer;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
import lv.venta.demo.models.EmployeeCourse;
import lv.venta.demo.models.Implementer;
import lv.venta.demo.models.Position;
import lv.venta.demo.repos.ICompanyRepo;
import lv.venta.demo.repos.ICourseCalendarRepo;
import lv.venta.demo.repos.ICourseImplementerRepo;
import lv.venta.demo.repos.ICourseRepo;
import lv.venta.demo.repos.ICourseTypeRepo;
import lv.venta.demo.repos.IDepartmentRepo;
import lv.venta.demo.repos.IEmployeeCourseRepo;
import lv.venta.demo.repos.IEmployeeRepo;
import lv.venta.demo.repos.IImplementerRepo;
import lv.venta.demo.repos.IPositionRepo;

@SpringBootApplication
public class VkTranzitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkTranzitsApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(IEmployeeRepo employeeRepo, IDepartmentRepo departmentRepo, 
										ICompanyRepo companyRepo,ICourseTypeRepo courseTypeRepo, 
										ICourseRepo courseRepo, IEmployeeCourseRepo employeeCourseRepo, 
										IPositionRepo positionRepo, IImplementerRepo implementerRepo,
										ICourseImplementerRepo courseImplementerRepo, ICourseCalendarRepo courseCalendarRepo)
	{
		return new CommandLineRunner() {
				
			public void run(String... args) throws Exception {
				

				Company com = new Company("Jhons");
				Company com1 = new Company("Does");
				Company com2 = new Company("Fixer");
				companyRepo.save(com);
				companyRepo.save(com1);
				companyRepo.save(com2);
				

				Position pos = new Position("Plumber","They do plumbin");
				Position pos1 = new Position("Electrician","They do electricing");
				Position pos2 = new Position("Pekachus","They do pika pi");
				positionRepo.save(pos);
				positionRepo.save(pos1);
				positionRepo.save(pos2);
				
				
				Department dep = new Department(com,"Plumbas");
				Department dep1 = new Department(com1,"Electricans");
				Department dep2 = new Department(com2,"Piekachus");
				departmentRepo.save(dep);
				departmentRepo.save(dep1);
				departmentRepo.save(dep2);
				
				Employee emp = new Employee("Jhon","Silver",22134570,"abce@gmail.com",dep,pos);
				Employee emp1 = new Employee("Jevgenijs","Saimnieks",22564540,"sert@gmail.com",dep1,pos1);
				Employee emp2 = new Employee("Janis","Sudrabs",22626561,"asdf@gmail.com",dep1,pos1);
				Employee emp3 = new Employee("Kristaps","Slieka",22134560,"rtyh@gmail.com",dep2,pos);
				Employee emp4 = new Employee("Maris","Smiekls",22555660,"zxcv@gmail.com",dep2,pos2);
				Employee emp5 = new Employee("Lone","Man",22512310,"abece@gmail.com",dep,pos2);
				Employee emp7 = new Employee("Dude","Mane",22525250,"abolds123@gmail.com",dep1,pos);
				employeeRepo.save(emp);
				employeeRepo.save(emp1);
				employeeRepo.save(emp2);
				employeeRepo.save(emp3);
				employeeRepo.save(emp4);
				employeeRepo.save(emp5);
				employeeRepo.save(emp7);
				
				
				CourseType cty = new CourseType(false, "Ugunsdrosiba");
				CourseType cty1 = new CourseType(true, "Darba drosiba");
				courseTypeRepo.save(cty);
				courseTypeRepo.save(cty1);
				
				Course co = new Course(cty,new ArrayList<>(Arrays.asList(dep,dep1)),"Ugunsdrosiba","Viss galvenais par uguni");
				Course co1 = new Course(cty1,new ArrayList<>(Arrays.asList(dep2)),"Darba drosiba","Viss galvenais par darbu");
				courseRepo.save(co);
				courseRepo.save(co1);
				
				EmployeeCourse emc = new EmployeeCourse(emp7, co1, 4);
				EmployeeCourse emc1 = new EmployeeCourse(emp2, co, 10);
				employeeCourseRepo.save(emc);
				employeeCourseRepo.save(emc1);
				
				Implementer imp = new Implementer("Viktors Vikis");
				Implementer imp1 = new Implementer("Janis Vikis");
				implementerRepo.save(imp);
				implementerRepo.save(imp1);
				
				CourseCalendar cal = new CourseCalendar(2023, new Date(05112033),new Date(06122031));
				courseCalendarRepo.save(cal);
				
				
				CourseImplementer cim = new CourseImplementer(imp,  cal, "Not important");
				CourseImplementer cim1 = new CourseImplementer(imp1, cal, "Damn this hard");
				courseImplementerRepo.save(cim);
				courseImplementerRepo.save(cim1);
				
				
				
			}
		};
	}
}
