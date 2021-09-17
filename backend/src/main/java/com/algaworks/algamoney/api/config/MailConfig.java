package com.algaworks.algamoney.api.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.algaworks.algamoney.api.config.property.AlgamoneyApiProperty;

@Configuration
public class MailConfig {

	@Autowired
	private AlgamoneyApiProperty  algamoneyApiProperty;
	
	@Bean
	public JavaMailSender javaMailSender() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.connectiontimeout", 10000);
		
		JavaMailSenderImpl mailSender =  new JavaMailSenderImpl();
		mailSender.setJavaMailProperties(props);
		mailSender.setHost(algamoneyApiProperty.getMail().getHost());
		mailSender.setPort(algamoneyApiProperty.getMail().getPort());
		mailSender.setUsername(algamoneyApiProperty.getMail().getUsername());
		mailSender.setPassword(algamoneyApiProperty.getMail().getPassword());
		
		return mailSender;
	}
}
