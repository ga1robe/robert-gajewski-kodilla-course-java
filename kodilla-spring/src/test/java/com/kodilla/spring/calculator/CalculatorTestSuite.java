package com.kodilla.spring.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculatorTestSuite {

    @Test
    void testCalculations() {
        /**
         * Given
         */
        ApplicationContext context = new AnnotationConfigApplicationContext("com.kodilla.spring");
        Calculator calculator = context.getBean(Calculator.class);
        /**
         * When
         */
        System.out.print("10 + 10 = ");
        double resultAdd = calculator.add(10, 10);

        System.out.print("10 - 10 = ");
        double resultSub = calculator.sub(10,10);

        System.out.print("10 * 10 = ");
        double resultMul = calculator.mul(10,10);

        System.out.print("10 / 10 = ");
        double resultDiv = calculator.div(10,10);
        //Then
        /**
         * Then
         */
        assertEquals(20, resultAdd, 0);
        assertEquals(0, resultSub, 0);
        assertEquals(100, resultMul, 0);
        assertEquals(1, resultDiv, 0);
    }
}
