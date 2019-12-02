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
			System.out.println("create new Students!");

			Student theStudent0 = new Student("Lahcen", "Elalem", "lelalem@free.fr");
			Student theStudent1 = new Student("Anouar", "Elalem", "aelalem@free.fr");
			Student theStudent2 = new Student("Yasmine", "Elalem", "yelalem@free.fr");
			Student theStudent3 = new Student("Cherifa", "Elalem", "celalem@free.fr");

			// start the transaction
			session.beginTransaction();

			// save student objects
			session.save(theStudent0);
			session.save(theStudent1);
			session.save(theStudent2);
			session.save(theStudent3);

			// commit the transaction (the change)
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

