package com.rikkeisoft.vn.dao;

import java.util.List;

import com.rikkeisoft.vn.model.Employee;


public interface EmployeeDAO {
	
	List<Employee> get();
	
	Employee get(int id);
	
	void save(Employee employee);
	
	void delete(int id);
	

}
