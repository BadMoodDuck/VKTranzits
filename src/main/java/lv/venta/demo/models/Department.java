package lv.venta.demo.models;

import java.util.ArrayList;
import java.util.Collection;

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
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IdDe")
	@Setter(value = AccessLevel.NONE)
	private int idDe;
	
	//TODO Validacijas
	@Column(name="Name")
	@Size(min=1, max=40)
	@Pattern(regexp="[a-zA-Z]+(.|\\s)*")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="IdCo")
	private Company company;
	
	@OneToMany(mappedBy = "department")
	@ToString.Exclude
	private Collection<Employee> employees;
	
	@ManyToMany
	@JoinTable(joinColumns=@JoinColumn(name = "IdDe"),
			inverseJoinColumns = @JoinColumn(name="IdCou"))
	@ToString.Exclude
	private Collection<Course> courses = new ArrayList<Course>();

	public Department(Company company,String name) {
    
		this.name = name;
		this.company = company;
	}
	
	public void addNewCourse(Course course)
	{
		courses.add(course);
	}
	
	
}
