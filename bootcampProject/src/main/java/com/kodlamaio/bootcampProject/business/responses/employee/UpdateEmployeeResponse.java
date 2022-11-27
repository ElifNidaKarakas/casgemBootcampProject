package com.kodlamaio.bootcampProject.business.responses.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeResponse {
	private int id;

	private String firstName;

	private String lastName;

	private String email;

	private String position;
	private String password;
}
