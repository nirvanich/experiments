package com.udemy.java.interfacepolymorphism;

public class GoogleMini implements Alarm{

    public void ask(){
        System.out.println("How is the weather outside?");
    }

    public void setAlarm(){
        System.out.println("Setting and alarm @7:30AM on GoogleMini");
    }

}
