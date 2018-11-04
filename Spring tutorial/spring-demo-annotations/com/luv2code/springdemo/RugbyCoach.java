package com.luv2code.springdemo;

public class RugbyCoach implements Coach
{

    private FortuneService fortuneService;
    public RugbyCoach(FortuneService fortuneService){
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Train hard 45 minutes";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
