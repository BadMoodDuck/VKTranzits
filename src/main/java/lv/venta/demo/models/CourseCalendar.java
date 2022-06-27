package lv.venta.demo.models;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Table
@Entity
public class CourseCalendar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdCal")
	@Setter(value = AccessLevel.NONE)
	private int idCal;

	@Min(value = 2022)
	@Max(value = 2050)
	@Column(name = "Year")
	private int year;

	// TODO validacijas
	@Column(name = "StartDate")
	private Date startDate;

	@Column(name = "EndDate")
	private Date endDate;

	@OneToMany(mappedBy = "courseCalendar")
	@ToString.Exclude
	private Collection<CourseImplementer> courseImplementers;

	@ManyToMany(mappedBy = "calendars")
	@ToString.Exclude
	private Collection<Position> positions = new ArrayList<Position>();

	public CourseCalendar(int year, Date startDate, Date endDate) {

		this.year = year;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public void addPosition(Position position) {
		positions.add(position);
	}

}
