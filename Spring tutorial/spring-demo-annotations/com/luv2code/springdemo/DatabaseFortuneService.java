package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Practice Activity 5 - Dependency Injection with Annotations

@Component
public class DataBaseFortuneService implements FortuneService
{
    private String fileName = "C:\\Users\\kacpe\\Desktop\\spring-demo-annotations\\Fortunes";
    private List<String> theFortunes;

    // create a random number generator
    private Random myRandom = new Random();

    public DataBaseFortuneService() {

        File theFile = new File(fileName);

        System.out.println("Reading fortunes from file: " + theFile);
        System.out.println("File exists: " + theFile.exists());

        // initialize array list
        theFortunes = new ArrayList<String>();

        // read fortunes from file
        try (BufferedReader br = new BufferedReader(
                new FileReader(theFile))) {

            String tempLine;

            while ((tempLine = br.readLine()) != null) {
                theFortunes.add(tempLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFortune() {
        // pick a random string from the array
        int index = myRandom.nextInt(theFortunes.size());

        String theFortune = theFortunes.get(index);

        return theFortune;
    }
}
