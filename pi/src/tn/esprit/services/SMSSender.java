/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.util.Arrays;

/**
 *
 * @author USER
 */
public class SMSSender {

    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "ACcb3f7471a2d0f38e4c30dd596bb0566b";
    public static final String AUTH_TOKEN = "3531ad675fdec024237d6db2f5e68d8c";

    public void send() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21621072888"),
                new com.twilio.type.PhoneNumber("+16203082467"),
                "Un régime alimentaire est ajouté !")
            .create();

        System.out.println(message.getSid());
    }
}
    


 

