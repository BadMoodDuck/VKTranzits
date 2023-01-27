package lv.venta.demo.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class QuizEmployeeAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "IdQuEmAn")
	private int idQuEmAn; // Quiz Employee Answer ID
	
	@ManyToOne
	@JoinColumn(name = "employee")
	private Employee employee; // Employee 	
	
	@ManyToOne
	@JoinColumn(name = "quiz")
	private Quiz quiz; // Quiz
	
	@ManyToOne
	@JoinColumn(name = "quizQuestion")
	private QuizQuestion quizQuestion; //Quiz Question
	
	@ManyToMany
	@JoinTable(
	  joinColumns = @JoinColumn(name = "idQuAn"), 
	  inverseJoinColumns = @JoinColumn(name = "idQuEmAn"))
	@ToString.Exclude
	private Collection<QuizAnswers> quizAnswer = new ArrayList<QuizAnswers>();

	public QuizEmployeeAnswer(Employee employee, Quiz quiz, QuizQuestion quizQuestion)
	{
		this.employee = employee;
		this.quiz = quiz;
		this.quizQuestion = quizQuestion;
	}	
	public void AddNewQuizAnswer(QuizAnswers quizAnswer) {
		this.quizAnswer.add(quizAnswer);
	}
}
