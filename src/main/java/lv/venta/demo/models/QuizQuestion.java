package lv.venta.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.demo.enums.EnumQuestionTypes;

@Getter
@Setter
@NoArgsConstructor
@Table
@Entity
@ToString
public class QuizQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "IdQuQe")
	private int idQuQe;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "questionType")
	private EnumQuestionTypes questionType;
	
	@ManyToOne
	@JoinColumn(name = "IdQu")
	private Quiz quiz;
	
	public QuizQuestion(String question, EnumQuestionTypes questionType, Quiz quiz) {
		this.question = question;
		this.questionType = questionType;
		this.quiz = quiz;
	}
}
