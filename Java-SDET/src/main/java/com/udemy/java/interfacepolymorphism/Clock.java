package com.udemy.java.interfacepolymorphism;

import java.time.LocalDateTime;

public class Clock implements Alarm {

    public void showTime(){
        System.out.println("It is " + LocalDateTime.now());
    }

    public void setAlarm(){
        System.out.println("Setting and alarm @7:30AM on Clock");
    }
}
