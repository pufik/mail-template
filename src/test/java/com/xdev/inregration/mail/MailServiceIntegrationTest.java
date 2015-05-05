package com.xdev.inregration.mail;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icegreen.greenmail.util.GreenMail;
import com.xdev.template.email.Email;
import com.xdev.template.email.EmailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:test-application-context.xml" })
public class MailServiceIntegrationTest {

	private static final String EMAIL_SUBJECT = "Test Email subject";
	private static final String EMAIL_FROM = "iurii@example.com";
	private static final String TEST_TEMPLATE = "test-template";
	private static final String EXPECTED_NAME = "Iurii";
	private static final String EXPECTED_CONTENT = String.format("Hello my name is %s!!!", EXPECTED_NAME);

	@Resource(name = "greenMail")
	private GreenMail greenMail;

	@Resource(name = "emailService")
	private EmailService emailService;

	@Before
	public void setUp() {
		greenMail.reset();
	}

	@Test
	public void shouldSendEmailForTemplate() throws Exception {
		Map<String, Object> context = new HashMap<>();
		context.put("name", EXPECTED_NAME);

		Email email = new Email();
		email.setFrom(EMAIL_FROM);
		email.setSubject(EMAIL_SUBJECT);
		email.setTemplateUid(TEST_TEMPLATE);
		email.setType(Email.Type.HTML);
		email.getTo().add(EMAIL_FROM);
		email.getTo().add(EMAIL_FROM);
		email.setContext(context);

		emailService.sendEmail(email);
		MimeMessage actualEmailMessage = greenMail.getReceivedMessages()[0];

		assertEquals(BigDecimal.ONE.intValue(), greenMail.getReceivedMessages().length);
		assertEquals(EXPECTED_CONTENT, actualEmailMessage.getContent().toString().trim());
	}
}
