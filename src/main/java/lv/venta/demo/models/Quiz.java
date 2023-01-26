package lv.venta.demo.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "IdQu")
	private int idQu;
	
	@Column(name = "title")
	private String title;
	
	// (h2) Might not work with other databases 
	@Column(name = "creationDate")
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date creationDate;
	
	
	@Column(name = "completionDeadLine")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date completionDeadLine;
	
	@ManyToOne
	@JoinColumn(name="IdCou")
	private Course course;
	
	@OneToMany(mappedBy = "quiz")
	private Collection<QuizQuestion> quizQuestions = new ArrayList<QuizQuestion>();
	
	@OneToMany(mappedBy = "quiz")
	@ToString.Exclude
	private Collection<QuizEmployeeAnswer> quizEmployeeAnswer = new ArrayList<>();
	
	public Quiz(String title, Date completionDeadLine, Course course) {
		this.title = title;
		//this.creationDate = new Date(new java.util.Date().getTime());
		this.completionDeadLine = completionDeadLine;
		this.course = course;
	}
	
	public void RemoveQuizQuestion(QuizQuestion question) {
		quizQuestions.remove(question);
		System.out.println(quizQuestions);
	}
}
