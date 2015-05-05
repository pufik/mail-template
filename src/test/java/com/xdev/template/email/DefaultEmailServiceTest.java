package com.xdev.template.email;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;

import com.xdev.template.email.render.TemplateRenderer;

@RunWith(MockitoJUnitRunner.class)
public class DefaultEmailServiceTest {

	private static final String EXPECTED_CONTENT = "expected content";

	@Mock
	private JavaMailSender mailSender;

	@Mock
	private TemplateRenderer templateRenderer;

	@Mock
	private MimeMessage message;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@InjectMocks
	private DefaultEmailService unit = new DefaultEmailService();

	@Before
	public void setUp(){
		given(mailSender.createMimeMessage()).willReturn(message);
		doNothing().when(mailSender).send(message);
		
		given(templateRenderer.render(anyString(), anyObject())).willReturn(EXPECTED_CONTENT);
	}

	@Test
	public void shouldSendEmail() throws Exception {
		String from = "from";
		String to = "to";
		String cc = "cc";
		String bcc = "bcc";
		String subject = "subject";
		String templateUid = "template-uid";
		Object context = new Object();
		
		Email email = new Email();
		email.setFrom(from);
		email.getTo().add(to);
		email.getCc().add(cc);
		email.getBcc().add(bcc);
		email.setSubject(subject);
		email.setTemplateUid(templateUid);
		email.setContext(context);
		
		unit.sendEmail(email );
		
		verify(mailSender).createMimeMessage();
		verify(mailSender).send(message);
		verify(templateRenderer).render(templateUid, context);
	}
	
	@Test
	public void shouldThrowMessagingException() throws Exception {
		String from = "from";
		String to = "to";
		String cc = "cc";
		String bcc = "bcc";
		String subject = "subject";
		String templateUid = "template-uid";
		Object context = new Object();
		
		Email email = new Email();
		email.setFrom(from);
		email.getTo().add(to);
		email.getCc().add(cc);
		email.getBcc().add(bcc);
		email.setSubject(subject);
		email.setTemplateUid(templateUid);
		email.setContext(context);
		
		expectedException.expect(EmailException.class);
		doThrow(MessagingException.class).when(mailSender).send(message);
		
		unit.sendEmail(email );
	}
}
