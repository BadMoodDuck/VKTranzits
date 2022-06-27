package lv.venta.demo.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table
@Entity
public class EmployeeCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IdEmCo")
	@Setter(value = AccessLevel.NONE)
	private int idEmCo;
	
	@ManyToOne
	@JoinColumn(name="IdEm")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="IdCou")
	private Course course;
	
	//TODO Date on hold |Don't know what to do with this yet|
	//@Column(name="Date")
	//private Date date;
	
	@Column(name="ValuePr")
	private float valuePr;
	
	public EmployeeCourse(Employee employee, Course course, float valuePr) {
		this.employee = employee;
		this.course = course;
		this.valuePr = valuePr;
	}
	
	
	
}
