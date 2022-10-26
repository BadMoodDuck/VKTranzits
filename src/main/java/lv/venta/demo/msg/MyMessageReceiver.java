package lv.venta.demo.msg;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MyMessageReceiver {

	@RabbitListener(queues = MQConfig.queue)
	public void receiver(MyMessage message) {
		System.out.println(message);
	}

}
