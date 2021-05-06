package com.kodilla.good.patterns.challenges.eservice;

public class InfoServiceSMS implements InfoService{
    public void informationUserAboutOrder(User user){
        System.out.println("INFO SERVICE:\nSMS sent to " + user.getNickname() + "\n");
    }
}