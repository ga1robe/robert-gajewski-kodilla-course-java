package com.kodilla.testing.forum.statistics;

import com.kodilla.testing.forum.ForumComment;
import com.kodilla.testing.forum.ForumPost;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;

@Nested
@ExtendWith(MockitoExtension.class)
@DisplayName("TDD: Forum Statistics Test Suite")
public class ForumStatisticsTestSuite {
    private static int testCounter = 0;
    private static List<String> forumUserNames = new ArrayList<>();
    private static List<ForumPost> forumPosts = new ArrayList<ForumPost>();
    private static List<ForumComment> forumComments = new ArrayList<ForumComment>();
    @Mock
    private Statistics statisticsMock;
    @BeforeEach
    public void beforeEveryTest() {
        testCounter++;
        System.out.println("Preparing to execute test #" + testCounter);
        forumUserNames= generateListOfNUserNames(50);
        forumPosts = generateListOfNForumPost(100);
        forumComments = generateListOfNForumComment(200);
    }
    @Test
    void testForumStatisticsAverageOfPostsPerUserWhen0Posts() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumPosts = generateListOfNForumPost(0);
        forumComments = generateListOfNForumComment(0);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(0d, calculateStatistics.getAverageOfPostsPerUser());
    }
    @Test
    void testForumStatisticsAverageOfCommentsPerUserWhen0Posts() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumPosts = generateListOfNForumPost(0);
        forumComments = generateListOfNForumComment(0);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(0d, calculateStatistics.getAverageOfCommentsPerUser());
    }
    @Test
    void testForumStatisticsAverageCommentsPerPostWhen0Posts() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumPosts = generateListOfNForumPost(0);
        forumComments = generateListOfNForumComment(0);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(Double.NaN, calculateStatistics.getAverageCommentsPerPost());
    }
    @Test
    void testForumStatisticsAverageOfPostsPerUserWhen1000Posts() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumPosts = generateListOfNForumPost(1000);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(20d, calculateStatistics.getAverageOfPostsPerUser());
    }
    @Test
    void testForumStatisticsAverageOfCommentsPerUserWhen1000Posts() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumPosts = generateListOfNForumPost(1000);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(4d, calculateStatistics.getAverageOfCommentsPerUser());
    }
    @Test
    void testForumStatisticsAverageCommentsPerPostWhen1000Posts() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumPosts = generateListOfNForumPost(1000);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(0.2d, calculateStatistics.getAverageCommentsPerPost());
    }
    @Test
    void testForumStatisticsAverageOfPostsPerUserWhen0Comments() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumComments = generateListOfNForumComment(0);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(2d, calculateStatistics.getAverageOfPostsPerUser());
    }
    @Test
    void testForumStatisticsAverageOfCommentsPerUserWhen0Comments() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumComments = generateListOfNForumComment(0);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(0d, calculateStatistics.getAverageOfCommentsPerUser());
    }
    @Test
    void testForumStatisticsAverageCommentsPerPostWhen0Comments() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumComments = generateListOfNForumComment(0);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(0d, calculateStatistics.getAverageCommentsPerPost());
    }
    @Test
    void testForumStatisticsAverageOfPostsPerUserWhenLessCommentsThanPosts() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumComments = generateListOfNForumComment(100);
        forumPosts = generateListOfNForumPost(200);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(4d, calculateStatistics.getAverageOfPostsPerUser());
    }
    @Test
    void testForumStatisticsAverageOfCommentsPerUserWhenLessCommentsThanPosts() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumComments = generateListOfNForumComment(100);
        forumPosts = generateListOfNForumPost(200);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(2d, calculateStatistics.getAverageOfCommentsPerUser());
    }
    @Test
    void testForumStatisticsAverageCommentsPerPostWhenLessCommentsThanPosts() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumComments = generateListOfNForumComment(100);
        forumPosts = generateListOfNForumPost(200);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(0.5d, calculateStatistics.getAverageCommentsPerPost());
    }
    @Test
    void testForumStatisticsAverageOfPostsPerUserWhenMoreCommentsThanPosts() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumComments = generateListOfNForumComment(200);
        forumPosts = generateListOfNForumPost(100);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(2d, calculateStatistics.getAverageOfPostsPerUser());
    }
    @Test
    void testForumStatisticsAverageOfCommentsPerUserWhenMoreCommentsThanPosts() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumComments = generateListOfNForumComment(200);
        forumPosts = generateListOfNForumPost(100);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(4d, calculateStatistics.getAverageOfCommentsPerUser());
    }
    @Test
    void testForumStatisticsAverageCommentsPerPostWhenMoreCommentsThanPosts() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumComments = generateListOfNForumComment(200);
        forumPosts = generateListOfNForumPost(100);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(2d, calculateStatistics.getAverageCommentsPerPost());
    }
    @Test
    void testForumStatisticsAverageOfPostsPerUserWhen0Users() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumUserNames = generateListOfNUserNames(0);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(Double.NaN, calculateStatistics.getAverageOfPostsPerUser());
    }
    @Test
    void testForumStatisticsAverageOfCommentsPerUserWhen0Users() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumUserNames = generateListOfNUserNames(0);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(Double.NaN, calculateStatistics.getAverageOfCommentsPerUser());
    }
    @Test
    void testForumStatisticsAverageCommentsPerPostWhen0Users() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumUserNames = generateListOfNUserNames(0);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(2d, calculateStatistics.getAverageCommentsPerPost());
    }
    @Test
    void testForumStatisticsAverageOfPostsPerUserWhen100Users() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumUserNames = generateListOfNUserNames(100);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(1d, calculateStatistics.getAverageOfPostsPerUser());
    }
    @Test
    void testForumStatisticsAverageOfCommentsPerUserWhen100Users() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumUserNames = generateListOfNUserNames(100);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(2d, calculateStatistics.getAverageOfCommentsPerUser());
    }
    @Test
    void testForumStatisticsAverageCommentsPerPostWhen100Users() {
        /*
         * Given
         */
        CalculateStatistics calculateStatistics = new CalculateStatistics();
        forumUserNames = generateListOfNUserNames(100);
        when(statisticsMock.commentsCount()).thenReturn(forumComments.size());
        when(statisticsMock.postCount()).thenReturn(forumPosts.size());
        when(statisticsMock.userNames()).thenReturn(forumUserNames);
        /*
         * When
         */
        calculateStatistics.calculateAdvStatistics(statisticsMock);
        /*
         * Then
         */
        Assertions.assertEquals(1d, calculateStatistics.getAverageOfPostsPerUser());
    }
    private List<String> generateListOfNUserNames(int userNamesQuantity) {
        List<String> resultList = new ArrayList<>();
        for (int n = 1; n <= userNamesQuantity; n++) {
            String username = "username" + n;
            resultList.add(username);
        }
        return resultList;
    }
    private List<ForumPost> generateListOfNForumPost(int forumPostQuantity) {
        List<ForumPost> resultList = new ArrayList<>();
        Random theGenerator = new Random();
        int indexOfUserName = 0;
        if (forumUserNames.size() > 0)
            indexOfUserName = theGenerator.nextInt(forumUserNames.size());
        if (forumUserNames.size() == 0) return resultList;
        for (int n = 1; n <= forumPostQuantity; n++) {
            ForumPost forumPost = new ForumPost("Hello everyone, " + "this is my " + n + " contribution here!",forumUserNames.get(indexOfUserName));
            resultList.add(forumPost);
        }
        return resultList;
    }
    private List<ForumComment> generateListOfNForumComment(int forumCommentQuantity) {
        List<ForumComment> resultList = new ArrayList<>();
        Random theGenerator = new Random();
        int indexOfUserName = 0;
        int indexOfPosts = 0;
        if (forumUserNames.size() > 0)
            indexOfUserName = theGenerator.nextInt(forumUserNames.size());
        if (forumPosts.size() > 0)
            indexOfPosts = theGenerator.nextInt(forumPosts.size());
        if (forumUserNames.size() == 0 || forumPosts.size() == 0) return resultList;
        for (int n = 1; n <= forumCommentQuantity; n++) {
            ForumComment forumComment = new ForumComment(forumPosts.get(indexOfPosts), "Thank you for good words!", forumUserNames.get(indexOfUserName));
            resultList.add(forumComment);
        }
        return resultList;
    }
}
