package com.luv2code.hibernate.demo.employee.practice;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadEmployee
{
    public static void main(String[] args) {
        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        // create a session
        Session session = sessionFactory.getCurrentSession();

        try{
            // create a employee object
            System.out.println("Creating an employee object...");
            Employee employee = new Employee("Harold", "Bagenschtad", "Fedoreo");

            // start a transaction
            session.beginTransaction();

            // save the employee object
            System.out.println("Saving the employee object...");
            session.save(employee);

            // commit transaction
            session.getTransaction().commit();

            // now code to retrieve an employee object

            // find out the employee's id: primary key
            System.out.println("Saved student. Generated id: " + employee.getId());

            // now get a new session and start transaction
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // retrieve employee based on the id: primary key
            System.out.println("\nGetting employee with id: " + employee.getId());
            Employee employee1 = session.get(Employee.class, employee.getId());

            System.out.println("Get complete: " + employee1);

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            sessionFactory.close();
        }
    }
}
