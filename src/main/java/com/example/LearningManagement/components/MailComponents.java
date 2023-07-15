package com.example.LearningManagement.components;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class MailComponents {
    //시스템 구축 : 도메인 구매 -> 도메인에 해당하는 메일 서버 구축
    //SMTP : Mail Protocol
    private final JavaMailSender javaMailSender;

    //인증메일 확인 : text만 확인
    public void sendMailTest() {

        SimpleMailMessage msg = new SimpleMailMessage();
        //받는 메일, 보내는 메일 yml : rkdrudrn1031@gmail.com
        msg.setTo("kku1031@naver.com");
        msg.setSubject("나에게 쓰는 메일");
        msg.setText("메일 전송 성공");

        javaMailSender.send(msg);
    }

    //인증메일 : text뿐만 아니라 실질적 링크, HTML 태그도 보내져야함.
    public boolean sendMail(String mail, String subject, String text) {

        boolean result = false;

        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(mail);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text, true);
            }
        };
        try {
            javaMailSender.send(msg);
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
