package com.luv2code.hibernate.demo.employee.practice;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class CreateEmployee
{
    public static void main(String[] args) {
        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try{

            // create a employee object
            System.out.println("Creating an employee object...");
            Employee employee = new Employee("Mark", "Cukierberg", "ArmBook");

            // start a transaction
            session.beginTransaction();

            // save the employee object
            System.out.println("Saving the employee object...");
            session.save(employee);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");


        }finally{
            sessionFactory.close();
        }
    }

}
