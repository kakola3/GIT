package com.luv2code.springdemo;

import java.util.Random;

public class NewFortuneService implements FortuneService
{
    private String [] fortunes={
            "Fantastic!",
            "Amazing!",
            "Nice!"
    };

    @Override
    public String getFortune() {
        int idx = new Random().nextInt(fortunes.length);
        String random = (fortunes[idx]);
        return random;
    }

    public String[] getFortunes() {
        return fortunes;
    }

    public void setFortunes(String[] fortunes) {
        this.fortunes = fortunes;
    }
}
