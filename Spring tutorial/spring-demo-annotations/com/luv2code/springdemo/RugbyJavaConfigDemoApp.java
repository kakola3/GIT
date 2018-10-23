package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RugbyJavaConfigDemoApp
{
    public static void main(String[] args) {
        // read spring java configuration class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SportConfig.class);
        // get the bean from spring container
        Coach theCoach = context.getBean("swimCoach", RugbyCoach.class);
        // call a method on a bean
        System.out.println(theCoach.getDailyWorkout());

        // Practice activity 4 - get the bean from spring container
        //Coach handCoach = context.getBean("handCoach", Coach.class);
        // Practice activity 4 - call method on a bean
        //System.out.println(handCoach.getDailyWorkout());

        // call method to get the daily fortune
        System.out.println(theCoach.getDailyFortune());

        // call our new swim coach methods ... has the props values injected
       // System.out.println("email: " + ((SwimCoach) theCoach).getEmail());
       // System.out.println("team: " + ((SwimCoach) theCoach).getTeam());

        // close the context
        context.close();
    }
}
