package lv.venta.demo.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Getter
@Setter
@NoArgsConstructor
@Table
@Entity
public class Implementer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdImpl")
	@Setter(value = AccessLevel.NONE)
	private int idImpl;

	@Size(min = 4, max = 20)
	@Pattern(regexp = "[A-Z]{1}[a-z]+")
	@Column(name = "Name")
	private String name;

	@OneToMany(mappedBy = "implementer")
	@ToString.Exclude
	private Collection<CourseImplementer> CourseImplements;

	public Implementer(String name) {
		
		this.name = name;
	}

}