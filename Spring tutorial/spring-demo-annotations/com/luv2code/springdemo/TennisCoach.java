package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach
{
    @Autowired
    @Qualifier("dataBaseFortuneService")
    private FortuneService fortuneService;

    // define a default constructor

    public TennisCoach(){
        System.out.println(">> TennisCoach: inside default constructor");
    }

    // define a setter method
//    @Autowired
//    public void doSomeCrazyStuff(FortuneService fortuneService){
//        System.out.println(">> TennisCoach: inside doSomeCrazyStuff() method");
//        this.fortuneService = fortuneService;
//    }

//    @Autowired
//    public TennisCoach(FortuneService fortuneService){
//        this.fortuneService = fortuneService;
//    }

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
