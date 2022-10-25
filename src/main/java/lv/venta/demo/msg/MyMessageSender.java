package lv.venta.demo.msg;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MyMessageSender {

	 @Autowired
	    private RabbitTemplate template;

	    @Autowired
		private TopicExchange topic;
}
