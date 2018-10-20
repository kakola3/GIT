package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

@Component("handCoach")
public class HandBallCoach implements Coach
{
    @Override
    public String getDailyWorkout() {
        return "Throw 10 times";
    }
}
