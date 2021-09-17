package com.algaworks.algamoney.api.mail;

//import java.util.Arrays;
//import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

//import com.algaworks.algamoney.api.models.FinancialRelease;
//import com.algaworks.algamoney.api.repositories.FinancialReleaseRepository;



@Component
public class Mailer {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
//	@Autowired
//	private FinancialReleaseRepository financialReleaseRepository;

//	@EventListener
//	private void test(ApplicationReadyEvent event) {
//		this.sendEmail("marcelolcarvalho@gmail.com", Arrays.asList("marcelolcarvalho@gmail.com","tiagohernany@gmail.com"),
//				"Teste", "Olá!<br/>Teste ok.");
//		System.out.println("terminado o envio de e-mail");
//	}
	
//	@EventListener
//	private void test(ApplicationReadyEvent event) {
//		String template = "mail/notify-financial-overdue";
//		
//		List<FinancialRelease> list = financialReleaseRepository.findAll();
//		
//		Map<String ,Object> variaveis = new HashMap<>();
//		variaveis.put("lancamentos", list);
//		
//		this.sendEmail("marcelolcarvalho@gmail.com", Arrays.asList("marcelolcarvalho@gmail.com","tiagohernany@gmail.com"),
//				"Testando", template, variaveis);
//		System.out.println("terminado o envio de e-mail");
//	}
	
	public void sendEmail(String from, 
			List<String> to, String subject, String template, 
			Map<String, Object> variaveis) {
		
		Context context = new Context(new Locale("pt", "BR"));
		
		variaveis.entrySet().forEach(
				e -> context.setVariable(e.getKey(), e.getValue()));
		
		String message = thymeleaf.process(template, context);
		
		this.sendEmail(from, to, subject, message);
	}
	
	
	public void sendEmail(String from, 
			List<String> to, String subject, String message) {

		try {

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(from);
			helper.setTo(to.toArray(new String[to.size()]));
			helper.setSubject(subject);
			helper.setText(message, true);
			
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException("Problemas com o envio e e-mail!", e);
		}

	}
}
