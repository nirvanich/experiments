package com.udemy.calculator;

public class TestMain {
    public static int calculate(int OnScreenNumber, MathOperation mathOperation, int enteredNumber){
        return mathOperation.calculate(OnScreenNumber, enteredNumber);
    }

    public static void main(String[] args) {

        MathOperation add = Integer::sum;
        MathOperation subtract = (a, b) -> a - b;
        MathOperation multiply = (a, b) -> a * b;
        MathOperation divide = (a, b) -> a / b;

        // 5 + 2 - 3 * 7 + 2 / 3

        int result = calculate(5, add, 2);
        result = calculate(result, subtract, 3);
        result = calculate(result, multiply, 7);
        result = calculate(result, add, 2);
        result = calculate(result, divide, 3);

        System.out.println(result);





    }
}
