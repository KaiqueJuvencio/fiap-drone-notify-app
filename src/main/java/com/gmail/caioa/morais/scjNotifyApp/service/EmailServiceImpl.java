package com.gmail.caioa.morais.scjNotifyApp.service;

import com.gmail.caioa.morais.scjNotifyApp.entity.EmailDetalhes;
import com.gmail.caioa.morais.scjNotifyApp.service.interfaces.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    public String sendSimpleMail(EmailDetalhes detalhes) {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(detalhes.getRecipient());
            mailMessage.setText(detalhes.getMsgBody());
            mailMessage.setSubject(detalhes.getSubject());

            javaMailSender.send(mailMessage);
            log.info("#### Enviando e-mail:");
            log.info("## {}", mailMessage.toString());
            return "E-mail enviado com sucesso...";
        }
        catch (Exception e){
            throw e;
        }
    }

    public String sendMailComAnexo(EmailDetalhes detalhes) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(detalhes.getRecipient());
            mimeMessageHelper.setText(detalhes.getMsgBody());
            mimeMessageHelper.setSubject(detalhes.getSubject());

            FileSystemResource file = new FileSystemResource(new File(detalhes.getAttachment()));
            mimeMessageHelper.addAttachment(file.getFilename(), file);

            return "E-mail enviado com sucesso...";
        } catch (MessagingException e) {
            return "Erro na tentativa de enviar e-mail";
        }
    }
}
