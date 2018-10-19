package com.luv2code.springdemo;

public class VolleyBallCoach implements Coach {
    private FortuneService fortuneService;

    public void setFortuneService(FortuneService fortuneService){
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Serve 10 times!";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
