package com.example.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.EmployeeDAO;
import com.example.entity.Employee;


@Repository
public class EmployeeDaoImpl implements EmployeeDAO
{
	//Using Hibernate SessionFactory Implementation (i.e Hibernate API)
	@Autowired
	private SessionFactory sessionFactory;

	 @Transactional
	public void addEmployee(Employee employee) {
		this.sessionFactory.getCurrentSession().save(employee);
								//OR
		//this.sessionFactory.getCurrentSession().persist(employee);
	}

	//Hibernate API doesn't support Named Query
	 @Transactional
	public List<Employee> getAllEmployees() {
			Query q = this.sessionFactory.getCurrentSession().createQuery("from Employee");
			return q.list();
										//OR
			//return this.sessionFactory.getCurrentSession().createQuery("from Employee").list();
	}

	 @Transactional
	public void deleteEmployee(Integer employeeId) {
		this.sessionFactory.getCurrentSession().delete(this.sessionFactory.getCurrentSession().load(Employee.class,employeeId));
	}
}