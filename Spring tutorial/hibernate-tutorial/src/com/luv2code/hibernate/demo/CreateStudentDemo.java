package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.cfg.Configuration;


public class CreateStudentDemo
{
    public static void main(String[] args) {


        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(com.luv2code.hibernate.demo.entity.Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create a student object
            System.out.println("Creating new student object...");
            Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student...");
            session.delete(tempStudent);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

}





