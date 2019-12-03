package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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
			System.out.println("\nDeleting student with ID - " + studentId + " ...");
			Student myStudent = session.get(Student.class, studentId);
			if (myStudent != null) {
				session.delete(myStudent);
				System.out.println("student object deleted - id: " + studentId);
			} else {
				System.out.println("student with id " + studentId + " - doesn't exist");
			}
			System.out.println("commit the transaction ...");

			// commit this transaction
			session.getTransaction().commit();
			session.close();
			System.out.println("Done!\n");

			// #################   new transaction ... ################# 
			
			// get a other session & start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Another way of deleting ...
			System.out.println("\nAnother way of deleting student object");
			
			studentId = 2;
			System.out.println("Deleting student with ID - " + studentId + " ...");

			session.createQuery("delete from Student where id=2")
			.executeUpdate();
			
			// commit this transaction
			System.out.println("commit the transaction ...");
			session.getTransaction().commit();
			session.close();
			System.out.println("Done!\n");

			
			// END
			System.out.println("\nEND!\n");
			
		} catch (Exception excp) {
			excp.printStackTrace();

		} finally {
			factory.close();
		}
	}

}
