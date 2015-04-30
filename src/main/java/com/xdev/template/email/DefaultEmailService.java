package com.xdev.template.email;

import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.xdev.template.email.render.TemplateRenderer;

public class DefaultEmailService implements EmailService {

	private static Logger LOG = LoggerFactory.getLogger(DefaultEmailService.class);

	private JavaMailSender mailSender;

	private TemplateRenderer templateRenderer;

	@Override
	public void sendEmail(Email email) {
		Objects.requireNonNull(email, "Email should be provided");
		LOG.debug("Send email [{}]", email);

		try {
			MimeMessage mimeMessage = prepareMimeMessage(email);

			getMailSender().send(mimeMessage);
		} catch (MessagingException e) {
			LOG.error("Can't send email", e);
			throw new EmailException(e);
		}
	}

	protected MimeMessage prepareMimeMessage(Email email) throws MessagingException {
		MimeMessage mimeMessage = getMailSender().createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

		messageHelper.setFrom(email.getFrom());
		messageHelper.setSubject(email.getSubject());

		for (String to : email.getTo()) {
			messageHelper.addTo(to);
		}

		for (String cc : email.getCc()) {
			messageHelper.addCc(cc);
		}

		for (String bcc : email.getBcc()) {
			messageHelper.addBcc(bcc);
		}
		
		messageHelper.setText(getTemplateContent(email), Boolean.TRUE.booleanValue());

		return messageHelper.getMimeMessage();
	}

	protected String getTemplateContent(Email email) {
		return getTemplateRenderer().render(email.getTemplateUid(), email.getContext());
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public TemplateRenderer getTemplateRenderer() {
		return templateRenderer;
	}

	public void setTemplateRenderer(TemplateRenderer templateRenderer) {
		this.templateRenderer = templateRenderer;
	}
}
