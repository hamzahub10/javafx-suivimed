/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.services.OrdonnanceService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import tn.esprit.entities.ordonnance;


/**
 *
 * @author Dorra
 */
public class Email {
    public static void sendEmail(String s, ordonnance o, String med) {
        

      final String username = "98f46922cd7d2e";
      final String password = "f183ecc1189dd6";

      Properties props = new Properties();
      //props.setProperty("http.protocols", "TLSv1.2,TLSv1.3");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
      props.put("mail.smtp.port", "2525");

       Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {
          
          

         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(username));
         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(s));
         message.setSubject("vous avez une ordonnance");
         message.setText("cher Mr/Mme,"
            + "\n\n nous vous informons que vous avez une nouvelle ordonnance à la date "+o.getDate()+"."+ " \n\n votre médecin vous a prescrit "+o.getNb_paquet()+" boite(s) de "+med+" "+o.getDosage()+" avec les remarques suivantes: "+o.getRemarque()+"\n\n\n L'équipe Healthmate vous souhaite un bon rétablissement. \n\n Cordialement.");

         Transport.send(message);

         System.out.println("Mail sent successfully");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
    
}}
