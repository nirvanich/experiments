package com.udemy.java;

import com.udemy.java.lambda.GreetingSrvice;
import com.udemy.java.polymorphism.Dog;

import java.util.ArrayList;
import java.util.Arrays;

import static com.udemy.java.reusableMethods.Utilities.sortArray;

public class TestMain {
    public static void main(String[] args) {
        int a = 5;
        Dog d = new Dog();

        //regular interface implementation
        GreetingSrvice hiService = new GreetingSrvice() {
            @Override
            public void greet(String name) {
                System.out.println("Hi..." + name);
            }
        };

        //the same implementation but using lambda expression
        GreetingSrvice helloService = name -> System.out.println("Hello.." + name);

        helloService.greet("Mike");
    }




}
