package lv.venta.demo.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

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
@ToString
public class QuizAnswers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "IdQuAn")
	private int idQuAn;
	
	@ManyToOne
	@JoinColumn(name="IdQuQe")
	private QuizQuestion quizQuestion;
	
	@Column(name = "Answer")
	private String answer;
	
	@Column(name = "IsCorrect")
	private Boolean isCorrect;
	
//	@ManyToMany(mappedBy = "quizAnswers")
//	private Collection<EmployeeCourse> employeeCourse = new ArrayList<EmployeeCourse>();
	
	public QuizAnswers(QuizQuestion quizQuestion,String answer, boolean isCorrect) {
		this.quizQuestion = quizQuestion;
		this.answer = answer;
		this.isCorrect = isCorrect;
	}
}
