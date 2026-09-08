package com.devteria.notification.service;

import com.devteria.notification.dto.request.EmailRequest;
import com.devteria.notification.dto.request.SendEmailRequest;
import com.devteria.notification.dto.request.Sender;
import com.devteria.notification.dto.response.EmailResponse;
import com.devteria.notification.exception.AppException;
import com.devteria.notification.exception.ErrorCode;
import com.devteria.notification.repostitory.httpclient.EmailClient;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    EmailClient emailClient;
    String apiKey = "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";
    public EmailResponse sendEmail(SendEmailRequest request){
        Sender sender = Sender.builder()
                .name("Duck Snow")
                .email("ducksnow1462005@gmail.com")
                .build();
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(sender)
                .to(List.of(request.getTo()))
                .htmlContent(request.getHtmlContent())
                .subject(request.getSubject())
                .build();
        try {
            return emailClient.sendEmail(apiKey, emailRequest);
        }
        catch (FeignException e){
            throw new AppException(ErrorCode.CAN_NOT_SEND_EMAIL);
        }
    }
}
