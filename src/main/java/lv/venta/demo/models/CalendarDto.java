package lv.venta.demo.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDto {
	
	 	private Date startDate;
	    private Date endDate;
	    private String courseName;
	    
}
