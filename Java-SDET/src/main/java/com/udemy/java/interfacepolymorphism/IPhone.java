package com.udemy.java.interfacepolymorphism;

public class IPhone implements Alarm{

    public void dial(){
        System.out.println("Calling 123...");
    }

    public void answerPhone(){
        System.out.println("Hello");
    }

    public void setAlarm(){
        System.out.println("Setting and alarm @7:30AM on iPhone");
    }
}
