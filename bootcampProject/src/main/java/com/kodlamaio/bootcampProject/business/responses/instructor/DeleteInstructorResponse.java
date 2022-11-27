package com.kodlamaio.bootcampProject.business.responses.instructor;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteInstructorResponse {
	private int id;
	private String companyName;

	private String firstName;

	private String lastName;

	private String email;

	private String password;
	private LocalDate dateOfBirth;

	private String nationalIdentity;

}
