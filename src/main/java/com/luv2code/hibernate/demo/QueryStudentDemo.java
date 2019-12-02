package com.luv2code.hibernate.demo;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create  session factory
		SessionFactory factory = new Configuration()
				.configure ("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();
		try {
			// start the transaction
			session.beginTransaction();
			
			
			// query Students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the result
			for (Student item : theStudents) {
				System.out.println("student-" + item.getId() + " - " + item);
			}
			
			// commit the transaction
			System.out.println("commit change!");
			session.getTransaction().commit();
			session.close();
			
			System.out.println("Done!");
			
		} catch (Exception excp) {
			excp.printStackTrace();

		} finally {
			factory.close();
		}
	}

}
