package com.luv2code.springdemo;

public class ActivityFortuneService implements FortuneService
{

    @Override
    public String getFortune() {
        return "It's activity fortune service for practice activity 7.";
    }
}
