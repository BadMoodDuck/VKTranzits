//package lv.venta.demo.models;
//
//import java.util.Collection;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Table
//@Entity
//@ToString
//public class QuizEmployeeAnswer {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Setter(value = AccessLevel.NONE)
//	@Column(name = "IdQuEmAn")
//	private int idQuEmAn; // Quiz Employee Answer ID
//	
//	@ManyToMany
//	@JoinColumn(name = "IdEm")
//	private int idEm; // Employee ID
//	
//	@ManyToOne
//	@JoinColumn(name = "IdQu")
//	private int quId; // Quiz ID
//	
//	@ManyToOne
//	@JoinColumn(name = "IdQuQu")
//	private int idQuQu; //Quiz Question ID
//	
//	private int EmployeeAnswer;
//	
//}
