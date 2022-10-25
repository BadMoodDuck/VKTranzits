package lv.venta.demo.msg;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MyMessageReceiver {

	@RabbitListener(queues = MyMessage.queue)
	public void receiveMessage(Message message) throws Exception {
		throw new Exception();
	}

}
