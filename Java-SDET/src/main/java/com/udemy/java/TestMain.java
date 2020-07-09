package com.udemy.java;

import com.udemy.java.interfacepolymorphism.Alarm;
import com.udemy.java.interfacepolymorphism.Clock;
import com.udemy.java.interfacepolymorphism.GoogleMini;
import com.udemy.java.interfacepolymorphism.IPhone;
import com.udemy.java.polymorphism.*;

import java.util.HashMap;

public class TestMain {
    public static void main(String[] args) {

/*        //reference123
        Dog d = new Dog();
        Cat c = new Cat();
        Horse h = new Horse();
        test(d);
        test(c);
        test(h);
*/

        GoogleMini g = new GoogleMini();
        IPhone i = new IPhone();
        Clock c = new Clock();
        alarmTest(g);
        alarmTest(i);
        alarmTest(c);

    }
        private static void alarmTest(Alarm i){
            i.setAlarm();
        }

}
