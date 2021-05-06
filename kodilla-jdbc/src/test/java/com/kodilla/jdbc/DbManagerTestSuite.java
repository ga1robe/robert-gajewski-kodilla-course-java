package com.kodilla.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class DbManagerTestSuite {

    @Test
    void testConnect() throws SQLException {
        /**
         * Given
         * When
         */
        DbManager dbManager = DbManager.getInstance();
        /**
         * Then
         */
        assertNotNull(dbManager.getConnection());
    }

    @Test
    void testSelectUsers() throws SQLException {
        /**
         * Given
         */
        DbManager dbManager = DbManager.getInstance();
        /**
         * When
         */
        String sqlQuery = "SELECT * FROM USERS";
        Statement statement = dbManager.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);
        /**
         * Then
         */
        int counter = 0;
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + ", " +
                    rs.getString("FIRSTNAME") + ", " +
                    rs.getString("LASTNAME"));
            counter++;
        }
        rs.close();
        statement.close();
        assertEquals(5, counter);
    }

    @Test
    void testSelectUsersAndPosts() throws SQLException {
        /**
         * Given
         */
        DbManager dbManager = DbManager.getInstance();
        /**
         * When
         */
        String sqlQuery = "SELECT U.FIRSTNAME, U.LASTNAME, COUNT(*) AS TOTAL_POSTS" + " " +
                "FROM ISSUES I JOIN USERS U on U.ID = I.USER_ID_ASSIGNEDTO" + " " +
                "GROUP BY U.ID " +" " +
                "HAVING COUNT(*) >= 2 " +" " +
                "ORDER BY U.ID";
        Statement statement = dbManager.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);
        /**
         * Then
         */
        int counter = 0;
        while (rs.next()) {
            System.out.println(rs.getString("FIRSTNAME") +
                    rs.getString("LASTNAME") + ", " +
                    rs.getInt("TOTAL_POSTS"));
            counter++;
        }
        rs.close();
        statement.close();
        assertEquals(3, counter);
    }
}
