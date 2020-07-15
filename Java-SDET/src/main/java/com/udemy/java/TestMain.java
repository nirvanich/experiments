package com.udemy.java;

import com.udemy.java.lambda.StringOperations;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    private static final List<String> list = new ArrayList<>();

    public static void main(String[] args) {

        StringOperations op1 = list::add;

        op1.accept("udemy");

        System.out.println(list);


    }




}
