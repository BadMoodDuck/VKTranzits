package lv.venta.demo.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;

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
public class CourseType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IdTy")
	@Setter(value = AccessLevel.NONE)
	private int idTy;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="IsObligatory")
	@NotNull
	private Boolean isObligatory;
	

	@Column(name="Description")
	@Size(min=3,max=256,message="Title must be between 3 and 256 characters")
	@Pattern(regexp="[a-zA-Z]+(.|\\s)*")
	private String description;
	
	@OneToMany(mappedBy = "coType")
	@ToString.Exclude
	private Collection<Course> courses;
	
	
	public CourseType(String title,boolean isObligatory, String description/*, Collection<Course> courses*/) {
		this.title = title;
		this.isObligatory = isObligatory;
		this.description = description;
		//this.courses = courses;
	}
}
