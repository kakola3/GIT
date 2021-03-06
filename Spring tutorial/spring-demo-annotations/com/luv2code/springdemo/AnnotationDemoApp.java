package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp
{
    public static void main(String[] args) {
        // read spring config file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // get the bean from spring container
        Coach theCoach = context.getBean("tennisCoach", Coach.class);
        // call a method on a bean
        System.out.println(theCoach.getDailyWorkout());

        // Practice activity 4 - get the bean from spring container
        //Coach handCoach = context.getBean("handCoach", Coach.class);
        // Practice activity 4 - call method on a bean
        //System.out.println(handCoach.getDailyWorkout());

        // call method to get the daily fortune
        System.out.println(theCoach.getDailyFortune());

        // close the context
        context.close();
    }
}
