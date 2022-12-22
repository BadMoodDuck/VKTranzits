package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.QuizAnswers;

public interface IQuizAnswers extends CrudRepository<QuizAnswers, Integer>{

}
