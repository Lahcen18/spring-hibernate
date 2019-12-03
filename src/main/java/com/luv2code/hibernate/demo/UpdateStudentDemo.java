package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		// create  session factory
		SessionFactory factory = new Configuration()
				.configure ("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();
		try {
			int studentId = 1;
						
			// now get a new session & start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve object
			System.out.println("\nGetting student with ID - " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			if (myStudent != null) {
				System.out.println("object retrieved from DB: " + myStudent);
				System.out.println("update student first name : " + myStudent.getFirstName()+ " => \"Scooby\"");
				myStudent.setFirstName("Scooby");
			} else {
				System.out.println("student with id " + studentId + " doesn't exist");
			}
			// commit this transaction
			System.out.println("commit the transaction ...");
			session.getTransaction().commit();
			session.close();
			
			// ############### new transaction ... ######################
			
			// now get another new session & start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			
			// update email fior all student
			System.out.println("\nupdate email for all students");
			session.createQuery("update Student set email='thestudent@gmail.com'")
			.executeUpdate();
			
			// commit this transaction
			System.out.println("commit the transaction ...");
			session.getTransaction().commit();
			session.close();
			System.out.println("Done!");
			
			// END
			System.out.println("\nEND!\n");
			
		} catch (Exception excp) {
			excp.printStackTrace();

		} finally {
			factory.close();
		}
	}

}
