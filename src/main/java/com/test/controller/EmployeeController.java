package com.test.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.dto.EmployeeDTO;
import com.test.model.Employee;
import com.test.service.IEmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class EmployeeController {

	int cant = 1000;
	static UUID uuid = UUID.randomUUID();
	private final IEmployeeService service;
	private final ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> findAll() {
		List<EmployeeDTO> lst = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
//		EmployeeDTO dto = new EmployeeDTO();
//		List<EmployeeDTO> lst = service.findAll().stream().map(e -> {
//			dto.setIdEmployee(e.getIdEmployee());
//			dto.setFirstName(e.getFirstName());
//			dto.setPaternalSurname(e.getPaternalSurname());
//			dto.setMaternalSurname(e.getMaternalSurname());
//			dto.setPhone(e.getPhone());
//			dto.setCurp(e.getCurp());
//			dto.setSex(e.getSex());
//			dto.setStatus(e.isStatus());
//			return dto;
//		}).collect(Collectors.toList());
		return new ResponseEntity<>(lst, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> findById(@PathVariable Integer id) {
		EmployeeDTO dto = this.convertToDto(service.findById(id));
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<EmployeeDTO> save(@RequestBody EmployeeDTO dto) {
		Employee obj = service.save(this.convertToEntity(dto));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getIdEmployee()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> update(@PathVariable Integer id, @RequestBody EmployeeDTO dto) {
		dto.setIdEmployee(id);
		Employee obj = service.update(this.convertToEntity(dto), id);
		return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// HATEOAS
	@GetMapping("/hateoas/{id}")
	public EntityModel<EmployeeDTO> findByIdHateoas(@PathVariable Integer id) {
		EntityModel<EmployeeDTO> resource = EntityModel.of(this.convertToDto(service.findById(id)));

		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
		resource.add(link1.withRel("employee-info"));
		return resource;
	}

	private EmployeeDTO convertToDto(Employee obj) {
		return modelMapper.map(obj, EmployeeDTO.class);
	}

	private Employee convertToEntity(EmployeeDTO obj) {
		return modelMapper.map(obj, Employee.class);
	}
	
}
