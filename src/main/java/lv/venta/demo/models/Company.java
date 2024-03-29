package lv.venta.demo.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IdCo")
	@Setter(value = AccessLevel.NONE)
	private int idCo;
	
	@Column(name="Name")
	@Size(min=1, max=40)
	@Pattern(regexp="[a-zA-Z]+(.|\\s)*")
	private String name;
	
	@OneToMany(mappedBy = "company")
	@ToString.Exclude
	private Collection<Department> department = new ArrayList<>();

	public Company(String name) {
		this.name=name;
	}
}
