package com.kodlamaio.bootcampProject.business.responses.instructor;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllInstructorResponse {
	private int id;
	private String companyName;

	private String firstName;

	private String lastName;

	private String email;
	private LocalDate dateOfBirth;

	private String nationalIdentity;

}
