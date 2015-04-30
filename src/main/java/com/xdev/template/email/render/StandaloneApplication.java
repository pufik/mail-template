package com.xdev.template.email.render;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xdev.template.email.Email;
import com.xdev.template.email.EmailService;

public class StandaloneApplication {
	
	private static final String TEST_TEMPLATE = "register";
	private static final String EXPECTED_NAME = "Iurii";

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		EmailService emailService = applicationContext.getBean("emailService", EmailService.class); 
		
		Map<String, Object> context = new HashMap<>();
		context.put("name", EXPECTED_NAME);
		context.put("products", Arrays.asList("Laptop Lenovo", "Iphone 9+", "Monitor Dell P2414H"));
		
		Email email = new Email();
		email.setFrom("iurii@example.com");
		email.setSubject("Congrats!!!");
		email.setTemplateUid(TEST_TEMPLATE);
		email.setType(Email.Type.HTML);
		email.getTo().add("iurii@example.com");
		email.getTo().add("iurii@example.com");
		
		email.setContext(context);
		
		emailService.sendEmail(email);
	}

}
