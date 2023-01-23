package com.perscholas.namedQueryDemo;

import com.perscholas.namedQueryDemo.controller.EmployeController;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		EmployeController ec = new EmployeController();
		// ec.createEmployeeTable();
		ec.findEmployeeByname();
		ec.findEmployeeById();
		ec.ShowOfficeCodes_AsDepartment();
	}
}
