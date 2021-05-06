package com.kodilla.stream.forumuser;

import java.time.LocalDate;
import java.util.Objects;

public final class ForumUser {
    private final int userID;
    private final String username;
    private final char sex;
    private final LocalDate dateOfBirth;
    private final int numberOfPostPublished;
    public ForumUser(int userID, String username, char sex, LocalDate dateOfBirth, int numberOfPostPublished) {
        this.userID = userID;
        this.username = username;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.numberOfPostPublished = numberOfPostPublished;
    }
    public int getUserID() { return userID; }
    public String getUsername() { return username; }
    public char getSex() { return sex; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public int getNumberOfPostPublished() { return numberOfPostPublished; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForumUser)) return false;
        ForumUser forumUser = (ForumUser) o;
        return getUserID() == forumUser.getUserID() && getSex() == forumUser.getSex() && getNumberOfPostPublished() == forumUser.getNumberOfPostPublished() && Objects.equals(getUsername(), forumUser.getUsername()) && Objects.equals(getDateOfBirth(), forumUser.getDateOfBirth());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getUserID(), getUsername(), getSex(), getDateOfBirth(), getNumberOfPostPublished());
    }
    @Override
    public String toString() {
        return "ForumUser {" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", dateOfBirth=" + dateOfBirth +
                ", numberOfPostPublished=" + numberOfPostPublished +
                '}';
    }
}
