package com.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.test.model.Employee;

public interface IEmployeeRepo extends JpaRepository<Employee, Integer> {
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Employee SET status = false WHERE id_employee = :id", nativeQuery = true)
	void deleteByIdEmp(Integer id);

}
