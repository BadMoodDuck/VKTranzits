package lv.venta.demo.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IdCou")
	@Setter(value = AccessLevel.NONE)
	private int idCou;

	@ManyToOne
	@JoinColumn(name="IdTy")
	private CourseType coType;
	
	@ManyToMany(mappedBy = "courses")
	@ToString.Exclude
	private Collection<Department> departments = new ArrayList<Department>();
	
	@Column(name="Title")
	@Size(min=3,max=100,message="Title must be between 3 and 100 characters")
	@Pattern(regexp="[a-zA-Z]+(.|\\s)*")
	private String title;
	
	@Column(name="Description")
	@Size(min=3,max=256,message="Title must be between 3 and 256 characters")
	@Pattern(regexp="[a-zA-Z]+(.|\\s)*")
	private String description;
	
	@OneToMany(mappedBy = "course")
	@ToString.Exclude
	private Collection<EmployeeCourse> emCourse;
	
	public Course(CourseType coType, Collection<Department> departments, String title, String description) {
		this.coType = coType;
		this.departments = departments;
		this.title = title;
		this.description = description;
	}
	
}
