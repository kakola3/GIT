package com.luv2code.hibernate.demo.employee.practice;

import com.luv2code.hibernate.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryEmployee
{
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try{
             // start a transcaction
             session.beginTransaction();

            // query employess
            List<Employee> employees = session.createQuery("from Employee where company='ArmBook'").getResultList();

            // display employees
            displayEmployees(employees);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            sessionFactory.close();
        }
    }

    private static void displayEmployees(List<Employee> employees) {
        for (Employee employee : employees){
            System.out.println(employee);
        }
    }

}
