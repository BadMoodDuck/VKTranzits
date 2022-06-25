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
	private String name;

	//TODO parejie mainigie
	@ManyToOne
	@JoinColumn(name="IdDe")
	private Department department;
	
	@OneToMany(mappedBy = "employee")
	@ToString.Exclude
	private Collection<EmployeeCourse> emCourse;
	
	@ManyToOne
	@JoinColumn(name="IdPos")
	private Position position;
		
}
