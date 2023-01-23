package com.perscholas.namedQueryDemo.controller;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import com.perscholas.namedQueryDemo.model.Employee;

import jakarta.persistence.TypedQuery;

public class EmployeController {
	private static ServiceRegistry registry;
	private static SessionFactory factory;

	public void createEmployeeTable() {
		/*
		 * FIRST SOLUTION SessionFactory factory = new
		 * Configuration().configure().buildSessionFactory(); Session session =
		 * factory.openSession();
		 * 
		 * Transaction t = session.beginTransaction(); Employee uone = new Employee();
		 * t.commit(); System.out.println("successfully saved"); factory.close();
		 * session.close();
		 */

		// SECOND SOLUTION

		try {

			// configuration
			Configuration conf = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));
			conf.addAnnotatedClass(Employee.class);
			// registry
			registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

			factory = conf.buildSessionFactory(registry);

		} catch (Throwable ex) {
			ex.printStackTrace();
		}

		Transaction tx = null;
		Session session = factory.openSession();

		try {

			tx = session.beginTransaction();
			// transient mode
			Employee e = new Employee();

			tx.commit();

			System.out.println("successfully saved");

		} catch (HibernateException ex) {

			ex.printStackTrace();
			tx.rollback();

		} finally {
			session.close();

		}
	}

	public void findEmployeeByname() {

		try {

			// configuration
			Configuration conf = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));
			conf.addAnnotatedClass(Employee.class);
			// registry
			registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

			factory = conf.buildSessionFactory(registry);

		} catch (Throwable ex) {
			ex.printStackTrace();
		}

		Transaction tx = null;
		Session session = factory.openSession();

		try {

			tx = session.beginTransaction();
			// ------------ Hibernate Named Query -------------
			@SuppressWarnings("deprecation")
			Query query = session.getNamedQuery("findEmployeeByName");
			query.setParameter("name", "Tom Thele");
			List<Employee> employees = query.getResultList();
			Iterator<Employee> itr = employees.iterator();
			while (itr.hasNext()) {
				Employee e = itr.next();
				System.out.println(e);
			}

			tx.commit();

			System.out.println("successfully saved");

		} catch (HibernateException ex) {

			ex.printStackTrace();
			tx.rollback();

		} finally {
			session.close();

		}
	}

	public void findEmployeeById() {

		try {

			// configuration
			Configuration conf = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));
			conf.addAnnotatedClass(Employee.class);
			// registry
			registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

			factory = conf.buildSessionFactory(registry);

		} catch (Throwable ex) {
			ex.printStackTrace();
		}

		Transaction tx = null;
		Session session = factory.openSession();

		try {

			tx = session.beginTransaction();
			// ------------ Hibernate Named Query -------------
			@SuppressWarnings("deprecation")
			Query query = session.getNamedQuery("get_Emp_name_by_id");
			query.setParameter("id", 3);
			List<Object[]> emName = query.getResultList();

			for (Object[] o : emName) {
				System.out.println(
						"Employee name: " + o[0] + " | Employee Salary: " + o[1] + " | Emp Job Title: " + o[2]);
			}

			tx.commit();

		} catch (HibernateException ex) {

			ex.printStackTrace();
			tx.rollback();

		} finally {
			session.close();

		}
	}

	public void ShowOfficeCodes_AsDepartment() {
		try {

			// configuration
			Configuration conf = new Configuration()
					.configure(new File("src/main/java/hibernate.cfg.xml"));
			conf.addAnnotatedClass(Employee.class);
			// registry
			registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

			factory = conf.buildSessionFactory(registry);

		} catch (Throwable ex) {
			ex.printStackTrace();
		}

		Transaction tx = null;
		Session session = factory.openSession();
		
		try {

			tx = session.beginTransaction();
			//------------  Hibernate Named Query   ------------- 
			@SuppressWarnings("deprecation")
			Query query = session.getNamedQuery("empDepAlias");		  
	        List<Object[]> emName = query.getResultList();   
 
			for (Object[] e : emName) {    
				System.out.println("OfficeCode: " + e[1] +
						" | Dep Name: " +e[3]+ " | Employee Name: " + e[2]);
			} 

			tx.commit();


		} catch (HibernateException ex) {

			ex.printStackTrace();
			tx.rollback(); 

		} finally {
			session.close();

		}
	}
}