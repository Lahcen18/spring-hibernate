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
			System.out.println("\ncreate new Student bgin new transaction");
			
			// new student object
			Student theStudent = new Student("Hamid", "Momo", "hmomo@gmail.com", "22/09/1960");

			// start the transaction
			session.beginTransaction();

			// save the student object
			System.out.println("save new Student in DB");
			session.save(theStudent);

			// commit the transaction (the change)
			System.out.println("commit transaction");
			session.getTransaction().commit();
			session.close();
			
			System.out.println("Done!\n");
		} catch (Exception excp) {
			excp.printStackTrace();

		} finally {
			factory.close();
		}
	}

}
