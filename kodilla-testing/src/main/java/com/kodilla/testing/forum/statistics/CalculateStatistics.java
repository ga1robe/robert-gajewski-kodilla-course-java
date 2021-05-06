package com.kodilla.testing.forum.statistics;

import com.kodilla.testing.forum.ForumUser;

import java.util.ArrayList;
import java.util.List;

public class CalculateStatistics {
    private List<String> forumUserNames = new ArrayList<>();
    private int countOfUserNames;
    private int countOfPosts;
    private int countOfComments;
    private double averageOfPostsPerUser;
    private double averageOfCommentsPerUser;
    private double averageCommentsPerPost;
    /*
    * getters and setters
     */
    public List<String> getForumUserNames() { return forumUserNames; }
    public int getCountOfUserNames() { return countOfUserNames; }
    public int getCountOfPosts() { return countOfPosts; }
    public int getCountOfComments() { return countOfComments; }
    public double getAverageOfPostsPerUser() {
        if (forumUserNames.size() > 0)
            return (double) countOfPosts / forumUserNames.size();
        else
            return Double.NaN;
    }
    public double getAverageOfCommentsPerUser() {
        if (forumUserNames.size() > 0)
            return (double) countOfComments / forumUserNames.size();
        else
            return Double.NaN;
    }
    public double getAverageCommentsPerPost() {
        if (countOfPosts > 0)
            return (double) countOfComments / countOfPosts;
        else
            return Double.NaN;
    }
    public void calculateAdvStatistics(Statistics statistics) {
        /*
        * Calculate Adverage statistics
         */
        this.forumUserNames = statistics.userNames();
        this.countOfUserNames = statistics.userNames().size();
        this.countOfPosts = statistics.postCount();
        this.countOfComments = statistics.commentsCount();
        if(countOfUserNames > 0)
            this.averageOfPostsPerUser = (double)getCountOfPosts() / getCountOfUserNames();
        else
            this.averageOfPostsPerUser = Double.NaN;
        if(countOfUserNames > 0)
            this.averageOfCommentsPerUser = (double)getCountOfComments() / getCountOfUserNames();
        else
            this.averageOfCommentsPerUser = Double.NaN;
        if(countOfPosts > 0)
            this.averageCommentsPerPost = (double)getCountOfComments() / getCountOfPosts();
        else
            this.averageCommentsPerPost = Double.NaN;

    }
    public void showStatistics() {
        /*
        * Show statistics
         */
        System.out.println("Average of posts per user: " + getAverageOfPostsPerUser());
        System.out.println("Average of comments per user: " + getAverageOfCommentsPerUser());
        System.out.println("Average of comments per post: " + getAverageCommentsPerPost());
    }
}
