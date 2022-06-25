package lv.venta.demo.models;
import java.util.Collection;

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

@Getter
@Setter
@NoArgsConstructor
@Table
@Entity
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdPos")
	@Setter(value = AccessLevel.NONE)
	private int idPos;

	@Size(min = 4, max = 20)
	@Pattern(regexp = "[A-Z]{1}[a-z]+")
	@Column(name = "Title")
	private String title;

	@Size(min = 4, max = 500)
	@Column(name = "Description")
	private String description;

	public Position(String title, String description) {
		
		this.title = title;
		this.description = description;
	}

}
