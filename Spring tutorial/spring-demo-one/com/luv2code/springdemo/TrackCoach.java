package com.luv2code.springdemo;

public class TrackCoach implements Coach {
    private FortuneService fortuneService;

    // empty constructor allows forget about small issue from MyApp class :/
    public TrackCoach(){

    }

    public TrackCoach(FortuneService fortuneService){
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k";
    }

    @Override
    public String getFortune() {
        return "TrackCoach is your lucky day!";
    }
}
