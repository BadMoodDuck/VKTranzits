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

	@Column(name="Title")
	@Size(min=1,max=100,message="Title must be between 1 and 100 characters")
	@Pattern(regexp="^(.|\\s)*[a-zA-Z]+(.|\\s)*$")
	private String title;
	
	@Column(name="Description")
	@Size(min=1,max=256,message="Title must be between 1 and 256 characters")
	@Pattern(regexp="^(.|\\s)*[a-zA-Z]+(.|\\s)*$") 
	private String description;
	
	@ManyToOne
	@JoinColumn(name="IdTy")
	private CourseType coType;
	
	@ManyToMany(mappedBy = "courses")
	private Collection<Department> departments = new ArrayList<Department>();
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	@ToString.Exclude
	private Collection<CourseCalendar> calendar;

	
	@OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
	@ToString.Exclude
	private Collection<EmployeeCourse> emCourse = new ArrayList<>();
	
	@OneToMany(mappedBy = "course")
	@ToString.Exclude
	private Collection<Quiz> quiz = new ArrayList<Quiz>();
	
	public Course(CourseType coType, String title, String description) {
		this.coType = coType;
		this.title = title;
		this.description = description;
	}
	
	public void addQuiz(Quiz quiz) {
		if (quiz != null) {
			this.quiz.add(quiz);
		}
	}
	
	public void removeCourseType()
	{
		coType = null;
	}

	public void removeDepartment(Department dep) {
		departments.remove(dep);
	}
	
	public void addDepartment(Department dep) {
		departments.add(dep);
	}
	
}
