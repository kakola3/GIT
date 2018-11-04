package com.luv2code.springdemo;

public class VolleyBallCoach implements Coach {
    private FortuneService fortuneService;

    public VolleyBallCoach(FortuneService fortuneService){
        this.fortuneService = fortuneService;
    }

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

    // practice activity 3 - add an init method
    public void methodStartupVolleyball(){
        System.out.println("VolleyBallCoach - inside methodStartupVolleyball method");
    }

    // practice activity 3 - add a destroy method
    public void methodCleanupVolleyball(){
        System.out.println("VolleyBallCoach - inside methodCleanupVolleyball method");
    }
}
