package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfigClassForRugby
{
    @Bean
    public FortuneService activityFortuneService(){
        return new ActivityFortuneService();
    }

    @Bean
    public Coach rugbyCoach(){
        return new RugbyCoach(activityFortuneService());
    }
}
