package lv.venta.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.demo.config.MyUserDetails;
import lv.venta.demo.models.MyUser;
import lv.venta.demo.msg.MQConfig;
import lv.venta.demo.msg.MyMessage;
import lv.venta.demo.msg.MyMessageReceiver;

@Controller
public class HomeController {

	
	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
		//MyMessage message = new MyMessage("Logged in successfully");
		//template.convertAndSend(MQConfig.exchange, MQConfig.routingKey, message);
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
			if (user.getEmployeeExists()) {
				System.out.println(user.getEmployeeId());
				System.out.println("employee exists");
				return "redirect:/u/" +user.getEmployeeId();
			}
		}
		catch (Exception e) {
			logger.debug(e.toString());
		}
		return "home";
	}
}
