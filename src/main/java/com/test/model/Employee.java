package com.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // (name="ALIAS") JPQL FROM ALIAS
//@Table(name="tbl_employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEmployee;

	@Column(length = 70, nullable = false)
	private String firstName;

	@Column(length = 70, nullable = false)
	private String paternalSurname;

	@Column(length = 70, nullable = false)
	private String maternalSurname;

	@Column(length = 30, nullable = false)
	private String curp;

	@Column(length = 15, nullable = false)
	private String phone;

	@Column(length = 20, nullable = false)
	private String sex;
	
	private boolean status;
}
