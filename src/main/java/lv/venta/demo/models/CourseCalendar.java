package lv.venta.demo.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private int year;

	// validacijas
	private Date startDate;

	private Date endDate;

	public CourseCalendar(int year, Date startDate, Date endDate) {

		this.year = year;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
