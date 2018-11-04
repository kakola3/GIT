package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelloApp {
    public static void main(String[] args) {

        // load the spring configuration file
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // retrieve bean from spring container
        Coach theCoach = context.getBean("myCoach", Coach.class);
        Coach yourCoach = context.getBean("yourCoach", Coach.class);
        Coach herCoach = context.getBean("herCoach", Coach.class);

        // call methods on the bean
        System.out.println(theCoach.getDailyWorkout());
        System.out.println(yourCoach.getDailyWorkout());
        System.out.println(herCoach.getDailyWorkout());

        // let's call our new method for fortunes
        System.out.println(theCoach.getDailyFortune());
        System.out.println(herCoach.getDailyFortune());

        // close the context
        context.close();
    }
}
