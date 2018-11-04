package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleDemoApp
{
    public static void main(String[] args) {
        // load the spring configuration file
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext.xml");
        // retrieve bean from spring container
        Coach theCoach = context.getBean("myCoach", Coach.class);

        // print out the results
        System.out.println(theCoach.getDailyWorkout());


        // practice activity 3 - retrieve bean from spring container
        Coach volleyBallCoach = context.getBean("volleyBallCoach", Coach.class);
        // practice activity 3 - print out the results
        System.out.println(volleyBallCoach.getDailyFortune());


        // close the context
        context.close();
    }
}
