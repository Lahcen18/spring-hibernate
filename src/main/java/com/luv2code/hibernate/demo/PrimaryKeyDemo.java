package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// create  session factory
		SessionFactory factory = new Configuration()
				.configure ("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();
		try {
			// create 3 student objects
			System.out.println("create new Students! (5 new student) & start new transaction");

			Student theStudent0 = new Student("Lechat", "Minouche", "mlechat@sfr.fr", "15/07/2002");
			Student theStudent1 = new Student("Pero", "Pepsi", "ppero@gmail.com", "25/07/2004");
			Student theStudent2 = new Student("Amin", "Bob", "bamin@sfr.fr", "30/03/1995");
			Student theStudent3 = new Student("Zakari", "Walid", "zwalid@free.fr", "17/11/198");
			Student theStudent4 = new Student("Jamal", "LeBoss", "jleboss@free.fr", "01/06/1984");

			// start the transaction
			session.beginTransaction();

			// save student objects
			System.out.println("write student object in DB!");
			session.save(theStudent0);
			session.save(theStudent1);
			session.save(theStudent2);
			session.save(theStudent3);
			session.save(theStudent4);

			// commit the transaction (the change)
			System.out.println("commit change!");
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

