package br.com.sistemamanutencao.emaintenance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.sistemamanutencao.emaintenance.exception.SpringEmaintenanceException;
import br.com.sistemamanutencao.emaintenance.model.entity.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class EmailSenderService {
	
    @Autowired
    private JavaMailSender javaMailSender;

    @Async
	public
    void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessage.setContent(notificationEmail.getSubject(), "text/html");
            messageHelper.setFrom("emaintenanceapplication@gmail.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setText(notificationEmail.getBody());
            messageHelper.setSubject(notificationEmail.getSubject());
        };
        try {
        	javaMailSender.send(messagePreparator);
            log.info("Email de ativação enviado!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new SpringEmaintenanceException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
    }

}
