package com.captaindebug.errortrack.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.captaindebug.errortrack.Publisher;

@Service
public class EmailPublisher implements Publisher {

	private static final Logger logger = LoggerFactory.getLogger(EmailPublisher.class);

	@Autowired
	private MailSender mailSender;

	@Autowired
	private SimpleMailMessage mailMessage;

	@Override
	public <T> boolean publish(T report) {

		logger.debug("Sending report by email...");
		boolean retVal = false;
		try {
			String message = (String) report;
			mailMessage.setText(message);
			mailSender.send(mailMessage);
			logger.debug("Email sent...");
			retVal = true;
		} catch (Exception e) {
			logger.error("Can't send email... " + e.getMessage(), e);
		}

		return retVal;
	}

}
