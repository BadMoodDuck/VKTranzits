package lv.venta.demo;


import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lv.venta.demo.enums.EnumQuestionTypes;
import lv.venta.demo.models.Company;
import lv.venta.demo.models.Course;
import lv.venta.demo.models.CourseCalendar;
import lv.venta.demo.models.CourseImplementer;
import lv.venta.demo.models.CourseType;
import lv.venta.demo.models.Department;
import lv.venta.demo.models.Employee;
import lv.venta.demo.models.EmployeeCourse;
import lv.venta.demo.models.Implementer;
import lv.venta.demo.models.MyUser;
import lv.venta.demo.models.MyUserAuthority;
import lv.venta.demo.models.Position;
import lv.venta.demo.models.Quiz;
import lv.venta.demo.models.QuizAnswers;
import lv.venta.demo.models.QuizEmployeeAnswer;
import lv.venta.demo.models.QuizQuestion;
import lv.venta.demo.repos.ICompanyRepo;
import lv.venta.demo.repos.ICourseCalendarRepo;
import lv.venta.demo.repos.ICourseImplementerRepo;
import lv.venta.demo.repos.ICourseRepo;
import lv.venta.demo.repos.ICourseTypeRepo;
import lv.venta.demo.repos.IDepartmentRepo;
import lv.venta.demo.repos.IEmployeeCourseRepo;
import lv.venta.demo.repos.IEmployeeRepo;
import lv.venta.demo.repos.IImplementerRepo;
import lv.venta.demo.repos.IMyAuthorityRepo;
import lv.venta.demo.repos.IMyUserRepo;
import lv.venta.demo.repos.IPositionRepo;
import lv.venta.demo.repos.IQuizAnswers;
import lv.venta.demo.repos.IQuizEmployeeAnswer;
import lv.venta.demo.repos.IQuizQuestion;
import lv.venta.demo.repos.IQuizRepo;

@SpringBootApplication
public class VkTranzitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkTranzitsApplication.class, args);
	}
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Bean
	public CommandLineRunner runner(IEmployeeRepo employeeRepo, IDepartmentRepo departmentRepo, 
										ICompanyRepo companyRepo,ICourseTypeRepo courseTypeRepo, 
										ICourseRepo courseRepo, IEmployeeCourseRepo employeeCourseRepo, 
										IPositionRepo positionRepo, IImplementerRepo implementerRepo,
										ICourseImplementerRepo courseImplementerRepo, ICourseCalendarRepo courseCalendarRepo,
										IMyUserRepo userRepo, IMyAuthorityRepo authorityRepo,
										IQuizRepo quizRepo, IQuizQuestion quizQuestionRepo,
										IQuizAnswers quizAnswersRepo, IQuizEmployeeAnswer quizEmployeeAnswerRepo)
	{
		return new CommandLineRunner() {
				
			public void run(String... args) throws Exception {
				MyUserAuthority auth1 = new MyUserAuthority("ROLE_ADMIN");
				MyUserAuthority auth2 = new MyUserAuthority("ROLE_IMPLEMENTER");
				MyUserAuthority auth3 = new MyUserAuthority("ROLE_EMPLOYEE");
				MyUserAuthority auth4 = new MyUserAuthority("ROLE_GUEST");
				
				Position pos = new Position("Plumber","They do plumbin");
				Position pos1 = new Position("Electrician","They do electricing");
				Position pos2 = new Position("Pekachus","They do pika pi");
				positionRepo.save(pos);
				positionRepo.save(pos1);
				positionRepo.save(pos2);
				
				
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
				
				Employee emp = new Employee("Jhon","Silver",22134570,"abce@gmail.com", dep,pos);
				Employee emp1 = new Employee("Jevgenijs","Saimnieks",22564540,"sert@gmail.com", dep1,pos1);
				Employee emp2 = new Employee("Janis","Sudrabs",22626561,"asdf@gmail.com", dep1,pos1);
				Employee emp3 = new Employee("Kristaps","Slieka",22134560,"rtyh@gmail.com", dep2,pos);
				Employee emp4 = new Employee("Maris","Smiekls",22555660,"zxcv@gmail.com", dep2,pos2);
				Employee emp5 = new Employee("Lone","Man",22512310,"abece@gmail.com", dep,pos2);
				Employee emp7 = new Employee("David","Martinez",22525250,"davidmartinez@arosaka.com", dep1,pos);
				employeeRepo.save(emp);
				employeeRepo.save(emp1);
				employeeRepo.save(emp2);
				employeeRepo.save(emp3);
				employeeRepo.save(emp4);
				employeeRepo.save(emp5);
				employeeRepo.save(emp7);
				
				MyUser user1 = new MyUser("admins", encoder.encode("admins"),emp);
				MyUser user2 = new MyUser("jevgenijs", encoder.encode("Fksd123"),emp1);
				MyUser user3 = new MyUser("janis123", encoder.encode("Fkwertyu1"),emp2);
				MyUser user4 = new MyUser("kristaps", encoder.encode("Password"),emp3);
				MyUser user5 = new MyUser("maris1234", encoder.encode("12345"),emp4);
				MyUser user6 = new MyUser("lone123", encoder.encode("111111111"),emp5);
				MyUser user7 = new MyUser("david123", encoder.encode("idontknow"),emp7);
				
				user1.addAuthority(auth1);
				user2.addAuthority(auth2);
				user3.addAuthority(auth3);
				user4.addAuthority(auth4);
				user5.addAuthority(auth4);
				user6.addAuthority(auth3);
				user7.addAuthority(auth3);
				
				userRepo.save(user1);
				userRepo.save(user2);
				userRepo.save(user3);
				userRepo.save(user4);
				userRepo.save(user5);
				userRepo.save(user6);
				userRepo.save(user7);
				
				auth1.addUser(user1);
				auth2.addUser(user2);
				auth3.addUser(user3);
				auth4.addUser(user4);
				auth4.addUser(user5);
				auth3.addUser(user6);
				auth3.addUser(user7);
				
				authorityRepo.save(auth1);
				authorityRepo.save(auth2);
				authorityRepo.save(auth3);
				authorityRepo.save(auth4);
				



				
				
				CourseType cty = new CourseType("Ugunsdrosiba", false, "Viss vajadzigais par ugunsdrosiba");
				CourseType cty1 = new CourseType("Darba drosiba", true, "Viss vajadzigais par darba drosiba");
				courseTypeRepo.save(cty);
				courseTypeRepo.save(cty1);
				
				Course co = new Course(cty,"1.Ugunsdrosibas ievads","Viss galvenais par uguni");
				Course co1 = new Course(cty1,"1.Darba drosibas ievads","Viss galvenais par darbu");
				courseRepo.save(co);
				courseRepo.save(co1);
				
				
				dep.addNewCourse(co);
				dep1.addNewCourse(co);
				dep2.addNewCourse(co1);
				departmentRepo.save(dep);
				departmentRepo.save(dep1);
				departmentRepo.save(dep2);
				
				EmployeeCourse emc = new EmployeeCourse(emp7, co1, 4);
				EmployeeCourse emc1 = new EmployeeCourse(emp2, co, 10);
				employeeCourseRepo.save(emc);
				employeeCourseRepo.save(emc1);
				
				Implementer imp = new Implementer("Viktors Vikis");
				Implementer imp1 = new Implementer("Janis Vikis");
				implementerRepo.save(imp);
				implementerRepo.save(imp1);
				
				//CourseCalendar cal = new CourseCalendar(2023, new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));
				//courseCalendarRepo.save(cal);
				CourseCalendar cal2 = new CourseCalendar(2023, new Date(122,10,21),new Date(122,10,28), co1);
				courseCalendarRepo.save(cal2);
				
				
				Quiz quiz1 = new Quiz("Best Quiz", null, co);
				quizRepo.save(quiz1);
				System.out.println(quiz1);
				
				QuizQuestion qq = new QuizQuestion("Are u smart?",EnumQuestionTypes.RADIO,quiz1);
				quizQuestionRepo.save(qq);
				System.out.println(qq);
				
				QuizAnswers qa = new QuizAnswers(qq, "Yes", false);
				QuizAnswers qa2 = new QuizAnswers(qq, "No", true);
				
				quizAnswersRepo.save(qa);
				quizAnswersRepo.save(qa2);
				System.out.println(qa);
				System.out.println(qa2);
				
				
				dep.addNewCourse(co);
				dep1.addNewCourse(co);
				dep2.addNewCourse(co1);
				departmentRepo.save(dep);
				departmentRepo.save(dep1);
				departmentRepo.save(dep2);
				
				
				CourseImplementer cim = new CourseImplementer(imp,  cal2, "Not important");
				CourseImplementer cim1 = new CourseImplementer(imp1, cal2, "Damn this hard");
				courseImplementerRepo.save(cim);
				courseImplementerRepo.save(cim1);
				
				
				authorityRepo.save(auth1);
				authorityRepo.save(auth2);
				authorityRepo.save(auth3);
				authorityRepo.save(auth4);
				
				QuizEmployeeAnswer emAnser = new QuizEmployeeAnswer(emp,quiz1,qq);
				emAnser.AddNewQuizAnswer(qa);
				quizEmployeeAnswerRepo.save(emAnser);

				QuizEmployeeAnswer emAnser1 = new QuizEmployeeAnswer(emp,quiz1,qq);
				emAnser1.AddNewQuizAnswer(qa2);
				quizEmployeeAnswerRepo.save(emAnser1);
				
				QuizEmployeeAnswer emAnser2 = new QuizEmployeeAnswer(emp2,quiz1,qq);
				emAnser2.AddNewQuizAnswer(qa);
				quizEmployeeAnswerRepo.save(emAnser2);
				
			}
		};
	}
}
