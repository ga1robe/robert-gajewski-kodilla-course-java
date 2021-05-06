package com.kodilla.testing.calculator;

public class Calculator {
    public int add(int a, int b) {
        if (validInteger(a) || validInteger(b) || validInteger(a + b))
            return (a+b);
        else
            return -1;
    }
    public int subtract(int a, int b) {
        if (validInteger(a) || validInteger(b) || validInteger(a-b))
            return (a-b);
        else
            return 0;
    }
    private Boolean validInteger(int x) {
        return x <= Integer.MAX_VALUE && x >= Integer.MIN_VALUE;
    }
}
