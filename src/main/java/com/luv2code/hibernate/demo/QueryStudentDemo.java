package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
			System.out.println("\nStart new tranaction ...");
			session.beginTransaction();
			
			
			// query Students
			System.out.println("create Query  to list all students");
			List<Student> theStudents = castList(Student.class, session.createQuery("from Student").getResultList());
			
			// display the result
			System.out.println("disply result :");
			displayStudents(theStudents);

			// query student : lastName = 'lechat'
			System.out.println("create Query  to retrieve all students with last name == \"lechat\"");
			theStudents = castList(Student.class, session.createQuery("from Student s where s.lastName='lechat'").getResultList());
			// display the result
			System.out.println("students with lastName = 'lechat' :");
			displayStudents(theStudents);

			
			// query students: firstName='anouar' OR email='yelalem@free.fr'"
			System.out.println("create Query  to retrieve all students with first name == \"Zakari\" OR email='jleboss@free.fr");
			theStudents = castList(Student.class, session.createQuery("from Student s where" +
							" s.firstName='Zakari' OR s.email='jleboss@free.fr'").getResultList());
			System.out.println("Students with firstName='Zakari' OR email='jleboss@free.fr' : ");
			displayStudents(theStudents);

			
			//query with LIKE
			System.out.println("create Query  using LIKE:");
			theStudents = castList(Student.class, session.createQuery("from Student s where s.email LIKE '%free.fr'").getResultList());
			System.out.println("result of query with LIKE : ");
			displayStudents(theStudents);

			
			// commit the transaction
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

	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student item : theStudents) {
			System.out.println("student-" + item.getId() + " - " + item);
		}
	}

}
