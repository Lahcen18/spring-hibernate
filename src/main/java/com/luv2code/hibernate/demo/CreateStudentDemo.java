package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		// create  session factory
		SessionFactory factory = new Configuration()
				.configure ("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();
		try {
			// create a new student object
			System.out.println("create new Student!");

			Student theStudent = new Student("Lahcen", "Elalem", "lelalem@free.fr");

			// start the transaction
			session.beginTransaction();

			// save the student object
			session.save(theStudent);

			// commit the transaction (the change)
			System.out.println("commit change!");

			session.getTransaction().commit();

			System.out.println("Done!");
		} catch (Exception excp) {
			excp.printStackTrace();

		} finally {
			session.close();
		}
	}

}
