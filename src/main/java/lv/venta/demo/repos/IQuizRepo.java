package lv.venta.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Quiz;

public interface IQuizRepo extends CrudRepository<Quiz, Integer>{

	ArrayList<Quiz> findByCourseIdCou(int courseId);

}
