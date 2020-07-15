package com.udemy.calculator;

public class TestMain {
    public static int calculate(MathOperation mathOperation){
        int a = 100;
        int b = 5;
        return mathOperation.calculate(a, b);
    }

    public static void main(String[] args) {

        MathOperation add = Integer::sum;
        MathOperation subtract = (a, b) -> a - b;
        MathOperation multiply = (a, b) -> a * b;
        MathOperation divide = (a, b) -> a / b;


        System.out.println(calculate(add));
        System.out.println(calculate(subtract));
        System.out.println(calculate(multiply));
        System.out.println(calculate(divide));


    }
}
