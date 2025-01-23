package com.test.service;

import java.util.List;

import com.test.model.Employee;

public interface IEmployeeService {

	Employee save(Employee employee);

	Employee update(Employee employee, int id);

	List<Employee> findAll();

	Employee findById(Integer id);

	void delete(Integer id);
}
