package com.allvibe.all_vibe.infrastructure.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmailHelper {
  private final JavaMailSender mailSender;

  public void sendMail(String destinity, String user, LocalDate date) {
    MimeMessage message = mailSender.createMimeMessage();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    String dateAppointment = date.format(formatter);
    String htmlContent = this.readHTMLTemplate(user, dateAppointment, destinity);

    try {
      message.setFrom(new InternetAddress("***9@gmail.com"));
      message.setSubject("Subscription confirmation");

      message.setRecipients(MimeMessage.RecipientType.TO, destinity);
      message.setContent(htmlContent, MediaType.TEXT_HTML_VALUE);

      mailSender.send(message);
      System.out.println("Email send");

    } catch (Exception e) {
      System.out.println("ERROR email could not be sent " + e.getMessage());

    }
  }
  
  private String readHTMLTemplate(String user, String date, String email) {
    // Indicar en donde se encuentra el template
    final Path path = Paths.get("src/main/resources/emails/email_template.html");

    try (var lines = Files.lines(path)) {
      var html = lines.collect(Collectors.joining());

      return html.replace("{name}", user).replace("{date}", date).replace("{emial}", email);
    } catch (IOException e) {
      System.out.println("No se pudo leer el html");
      throw new RuntimeException();
    }
  }
}
