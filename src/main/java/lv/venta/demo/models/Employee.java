package lv.venta.demo.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
	@Column(name="IdEm")
	@Setter(value = AccessLevel.NONE)
	private int idEm;
	
	//TODO Validacijas
	@Column(name="Name")
	@Size(min=1, max=40)
	@Pattern(regexp="[a-zA-Z]+(.|\\s)*")
	private String name;

	//TODO parejie mainigie
	@ManyToOne
	@JoinColumn(name="IdDe")
	private Department department;
	
	@OneToMany(mappedBy = "employee")
	@ToString.Exclude
	private Collection<EmployeeCourse> emCourse;
	
	public Employee(String name,Department department,Collection<EmployeeCourse> emCourse) {
		this.name=name;
		this.department=department;
		this.emCourse=emCourse;
		
	}
		
}
