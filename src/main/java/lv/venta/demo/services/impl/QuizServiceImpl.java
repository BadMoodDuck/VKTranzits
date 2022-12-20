package lv.venta.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Quiz;
import lv.venta.demo.repos.IQuizRepo;
import lv.venta.demo.services.IQuizService;

@Service
public class QuizServiceImpl implements IQuizService{

	@Autowired
	private IQuizRepo quizRepo;
	@Override
	public Quiz getQuizById(int id) {
		if (quizRepo.existsById(id)) {
			return quizRepo.findById(id).get();
		}
		return null;
	}

}
