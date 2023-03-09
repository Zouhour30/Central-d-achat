package pidev.elbey.Services;

import pidev.elbey.Entities.EmailDetails;

public interface EmailService {
	
	String sendSimpleMail(EmailDetails details);
	String sendMailWithAttachment(EmailDetails details);

}
