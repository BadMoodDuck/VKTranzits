package lv.venta.demo.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Table
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdEm")
	@Setter(value = AccessLevel.NONE)
	private int idEm;

	@Column(name = "Name")
	@Size(min = 1, max = 40)
	@Pattern(regexp = "[a-zA-Z]+(.|\\s)*")
	private String name;
	
	@Column(name = "Surname")
	@Size(min = 1, max = 40)
	@Pattern(regexp = "[a-zA-Z]+(.|\\s)*")
	private String surname;
	
	@Column(name = "Phone")
	@Digits(fraction = 0, integer = 8)
	private int phone;
	
	@Column(name = "Email")
	@Size(min = 1, max = 40)
	@Pattern(regexp = "[\\w-]+@[\\w-]+.+[\\w-]{2,4}$")
	private String email;
	
	@OneToOne(mappedBy = "employee")
	private MyUser user;

	@ManyToOne()
	@JoinColumn(name = "IdDe")
	private Department department;

	@OneToMany(mappedBy = "employee")
	@ToString.Exclude
	private Collection<EmployeeCourse> emCourses = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "IdPos")
	private Position position;
	
	@OneToMany(mappedBy = "employee")
	@ToString.Exclude
	private Collection<QuizEmployeeAnswer> quizEmployeeAnswer = new ArrayList<>();

	public Employee(String name,String surname,int phone, String email, Department department, Position position) {

		this.name = name;
		this.surname =surname;
		this.phone =phone;
		this.email =email;
		this.department = department;
		this.position = position;

	}
	
	public void addEmCourse(EmployeeCourse course) {
		emCourses.add(course);
	}
	
	public void removeEmCourse(EmployeeCourse course)
	{
		if(emCourses.contains(course)) {
		emCourses.remove(course);
		}
	}

}
