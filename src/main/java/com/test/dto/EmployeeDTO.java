package com.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmployeeDTO {

	@EqualsAndHashCode.Include
	private Integer idEmployee;
	private String firstName;
	private String paternalSurname;
	private String maternalSurname;
	private String curp;
	private String phone;
	private String sex;
	private boolean status;
	private String uuid;
	private int statusCode;
	private String message;
	
}
