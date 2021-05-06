package com.kodilla.exception.nullpointer;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
//        User user = new User("User1", 30, 200, "Test");
        User user = null;
        Optional<User> optionalUser = Optional.ofNullable(user);
        String username = optionalUser.orElse(new User("",0,0,"")).getName();
        System.out.println("Username: " + username);
        optionalUser.ifPresent(u -> System.out.println(u.getName()));
    }
}
