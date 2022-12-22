package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.QuizQuestion;

public interface IQuizQuestion extends CrudRepository<QuizQuestion, Integer>{

}
