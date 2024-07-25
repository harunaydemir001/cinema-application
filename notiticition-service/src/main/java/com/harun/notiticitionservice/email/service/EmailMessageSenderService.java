package com.harun.notiticitionservice.email.service;

import com.harun.notiticitionservice.email.config.EmailProviderConfig;
import com.harun.notiticitionservice.email.dto.EmailMessageDTO;
import com.harun.notiticitionservice.email.exception.EmailMessageSendingException;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Properties;


@Service
@RequiredArgsConstructor
public class EmailMessageSenderService {

    private static final Logger log = LoggerFactory.getLogger(EmailMessageSenderService.class);
    private final EmailProviderConfig emailProviderConfig;
    Logger logger = LoggerFactory.getLogger(EmailMessageSenderService.class);

    public void sendEmail(EmailMessageDTO emailMessageDTO) throws EmailMessageSendingException {
        try {
            Properties prop = new Properties();
            prop.putAll(emailProviderConfig.getProperties());


            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailProviderConfig.getAuth().getUsername(), emailProviderConfig.getAuth().getPassword());
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailMessageDTO.getFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailMessageDTO.getTo()));
            message.setSubject(emailMessageDTO.getSubject());

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(emailMessageDTO.getBody(), "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            logger.error(e.getMessage(), e);
            throw new EmailMessageSendingException(e.getMessage(), e);
        }
    }
}
