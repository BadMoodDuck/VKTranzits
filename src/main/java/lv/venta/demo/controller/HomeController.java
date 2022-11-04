package lv.venta.demo.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.demo.msg.MQConfig;
import lv.venta.demo.msg.MyMessage;
import lv.venta.demo.msg.MyMessageReceiver;

@Controller
public class HomeController {
	

	@Autowired
	private RabbitTemplate template;


	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	
	@GetMapping("")
	public String getDefault() {
		return "redirect:/home";
	}
	@GetMapping("/home")
	public String getHomePage() {
		System.out.println("homepage");
		MyMessage message = new MyMessage("Logged in successfully");
		template.convertAndSend(MQConfig.exchange, MQConfig.routingKey, message);
		return "home";
	}
}
