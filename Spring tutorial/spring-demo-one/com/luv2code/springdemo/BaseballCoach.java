package com.luv2code.springdemo;

public class BaseballCoach implements Coach{
    // define a private field for the dependency(zaleznosc)
    private FortuneService fortuneService;

    // define a constructor for dependency injection(wstrzykiwanie)
    public BaseballCoach(FortuneService fortuneService){
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout(){
        return "Spend 30 minutes on batting practice";
    }

    @Override
    public String getFortune() {
        // use my fortuneSerivice to get a fortune
        return  fortuneService.getFortune();

    }
}
