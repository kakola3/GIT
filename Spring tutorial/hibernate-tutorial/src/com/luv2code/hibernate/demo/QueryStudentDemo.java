package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo
{
    public static void main(String[] args) {


        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            // display the students
            displayTheStudents(theStudents);

            // query students: lastName = 'Duck'
            theStudents = session.createQuery("from Student s where s.lastName='Duck'").getResultList();

            // display the students
            System.out.println("\n\nStudents who have last name of Duck");
            displayTheStudents(theStudents);

            // query students: lastName = 'Duck' or firstName = 'Mary'
            theStudents = session.createQuery("from Student s where" +
                    " s.lastName='Duck' OR s.firstName='Mary'").getResultList();

            // display the students
            System.out.println("\n\nStudents who have last name of Duck OR first name Mary");
            displayTheStudents(theStudents);

            // query students where email LIKE '%luv2code.com'
            theStudents = session.createQuery("from Student s where " +
                    "s.email LIKE '%luv2code.com'").getResultList();

            // display the students
            System.out.println("\n\nStudents whose email ends with 'luv2code.com'");
            displayTheStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

    private static void displayTheStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }

}





