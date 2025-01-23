package com.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.model.Employee;
import com.test.repo.IEmployeeRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

	// @Autowired
	private final IEmployeeRepo repo;

	@Override
	public Employee save(Employee employee) {
		return repo.save(employee);
	}

	@Override
	public Employee update(Employee employee, int id) {
		return repo.save(employee);
	}

	@Override
	public List<Employee> findAll() {
		return repo.findAll();
	}

	@Override
	public Employee findById(Integer id) {
		Optional<Employee> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Employee();
	}

	@Override
	public void delete(Integer id) {
		repo.deleteByIdEmp(id);
		//repo.deleteById(id);
	}

}
