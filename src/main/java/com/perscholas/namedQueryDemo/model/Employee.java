package com.perscholas.namedQueryDemo.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "employee")
//Using @NamedQuery for single  HQL
@NamedQuery(name="getallEmployee", query="select e from Employee e")

//Using @NamedQueries for multiple  HQL

@NamedQuery(name="get_Emp_name_by_id", query="select e.name,e.salary,e.job from Employee e where id=:id")
@NamedQuery(name="get_all_dept", query="from Employee")
@NamedQuery( name = "findEmployeeByName",  query = "from Employee e where e.name = :name" )
@NamedQuery(name ="empDepAlias", query="select e, e.officeCode , e.name, CASE When (e.officeCode = '1') THEN 'IT'"
			+ "WHEN (e.officeCode = '6') THEN 'Admin'"
			+ "WHEN (e.officeCode = '5') THEN 'HR'"
			+ "WHEN (e.officeCode = '4') THEN 'Developers'"
			+ "WHEN(e.officeCode = '3') THEN 'Accounts'"
			+ "WHEN (e.officeCode ='2') THEN 'Finanace'"
			+ "ELSE 'General' END FROM Employee e  ")

public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@NonNull
	String name;
	@NonNull
	int salary;
	@NonNull
	String job;
	@NonNull
	String addressLine;
	@NonNull
	String zipcode;
	@NonNull
	String city;
	@NonNull
	Date startDate;
	@NonNull
	int officeCode;
}
