package com.kodilla.exception.nullpointer;

public class NullPointerExceptionRunner {
    public static void main(String[] args) throws MessageNotSentException {
        User user = null;
        MessageSender messageSender = new MessageSender();
        try {
            messageSender.sendMessageTo(user, "Hello!");
        } catch (MessageNotSentException e) {
            System.out.println(e.toString());
            if (e.getMessage() != null) System.out.println("Exception Message: " + e.getMessage());
            System.out.println("Message is not send, " + "but my program still running very well!");
        }
        System.out.println("Processing other logic.");
    }
}
