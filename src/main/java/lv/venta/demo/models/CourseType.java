package lv.venta.demo.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class CourseType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IdTy")
	@Setter(value = AccessLevel.NONE)
	private int idTy;
	
	@Column(name="IsObligatory")
	private boolean isObligatory;
	
	@Column(name="Description")
	private String description;
	
	@OneToMany(mappedBy = "coType")
	@ToString.Exclude
	private Collection<Course> courses;
	
}
