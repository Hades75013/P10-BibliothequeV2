package com.ocr.library.batch.step;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;
import java.util.List;

public class PretItemWriterMail implements ItemWriter<MimeMessage> {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void write(List<? extends MimeMessage> messages) throws Exception {

        messages.stream().forEach((message)->mailSender.send(message));
        //messages.stream().close();

    }

}
