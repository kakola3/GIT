package com.luv2code.springdemo;

public class FootballCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Give 10 shots";
    }

    @Override
    public String getFortune() {
        return null;
    }
}
