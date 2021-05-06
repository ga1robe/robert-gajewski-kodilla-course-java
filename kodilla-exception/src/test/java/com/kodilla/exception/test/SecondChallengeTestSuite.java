package com.kodilla.exception.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SecondChallengeTestSuite {

    @Test
    void testSecondChallangeToDoesNotThrow() {
        /**
         * GIVEN
         */
        SecondChallenge secondChallenge = new SecondChallenge();
        /**
         * WHEN & THEN
         */
        assertDoesNotThrow(
                () -> {
                    double x = 1.0;
                    double y =1.4;
                    secondChallenge.probablyIWillThrowException(x, y);
                }
        );
    }

    @Test
    void testSecondChallangeToAllForFirstVarChangeAndSecondVarConst() {
        /**
         * GIVEN
         */
        SecondChallenge secondChallenge = new SecondChallenge();
        /**
         * WHEN & THEN
         */
        assertAll(
                () -> {
                    double x = 1.0;
                    double y = 1.4;
                    secondChallenge.probablyIWillThrowException(x, y);
                },
                () -> {
                    double x = 1.2;
                    double y = 1.4;
                    secondChallenge.probablyIWillThrowException(x, y);
                },
                () -> {
                    double x = 1.4;
                    double y = 1.4;
                    secondChallenge.probablyIWillThrowException(x, y);
                },
                () -> {
                    double x = 1.6;
                    double y = 1.4;
                    secondChallenge.probablyIWillThrowException(x, y);
                },
                () -> {
                    double x = 1.8;
                    double y = 1.4;
                    secondChallenge.probablyIWillThrowException(x, y);
                },
                () -> {
                    double x = 1.8;
                    double y = 1.4;
                    secondChallenge.probablyIWillThrowException(x, y);
                },
                () -> {
                    double x = 1.9;
                    double y = 1.4;
                    secondChallenge.probablyIWillThrowException(x, y);
                },
                () -> {
                    double x = 1.99;
                    double y = 1.4;
                    secondChallenge.probablyIWillThrowException(x, y);
                }
        );
    }
}
