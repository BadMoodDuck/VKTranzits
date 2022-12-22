package lv.venta.demo.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Table
@Entity
public class CourseImplementer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdCouImpl")
	@Setter(value = AccessLevel.NONE)
	private int idCouImpl;

	@Size(min = 4, max = 300)
	@Pattern(regexp = "[a-zA-Z]+(.|\\s)*")
	@Column(name = "Notes")
	private String notes;

	@ManyToOne
	@JoinColumn(name = "IdImpl")
	private Implementer implementer;
	
	@ManyToOne
	@JoinColumn(name = "IdCal")
	private CourseCalendar courseCalendar;

	public CourseImplementer(Implementer implementer, CourseCalendar courseCalendar, String notes) {
		
		this.implementer = implementer;
		this.courseCalendar =courseCalendar;
		this.notes = notes;

	}
	
	public void removeImplementer() { //TODO: Check
		implementer = new Implementer();
	}

}