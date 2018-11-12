package com.luv2code.hibernate.demo.employee.practice;

import com.luv2code.hibernate.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteEmployee
{
    public static void main(String[] args) {
        // create a session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        // create a session
        Session session = sessionFactory.getCurrentSession();

        try{
            int id = 3;

            // now get a new session and transaction
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // retrieve an employee based on the id: primary key
            System.out.println("\nGetting employee with id: " + id);
            Employee employee = session.get(Employee.class, id);

            System.out.println("Deleting employee with id: " + id);
            session.delete(employee);

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            sessionFactory.close();
        }

    }

}
