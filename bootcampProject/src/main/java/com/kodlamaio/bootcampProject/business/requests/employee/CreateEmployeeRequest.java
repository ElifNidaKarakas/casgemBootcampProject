package com.kodlamaio.bootcampProject.business.requests.employee;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {

	@NotNull
	@NotBlank(message = "bos gecmeyin")
	@Length(min = 2, max = 50, message = "en az 3 karakter olmalı")
	private String firstName;

	@NotNull
	@NotEmpty(message = "bos gecmeyiniz")
	private String lastName;

	@NotNull
	@NotEmpty(message = "bos gecmeyiniz")
	@Email(regexp = ".+[@].+[\\.].+", message = "Email must be valid")
	private String email;

	@NotNull
	@NotEmpty(message = "bos gecmeyiniz")
	@Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password must be minimum 8 characters, at least one letter, one number and one special character:")
	private String password;
	@NotNull
	@NotEmpty(message = "bos gecmeyiniz")
	@Length(min = 2, max = 50, message = "")
	private String position;

	@NotNull
	@NotEmpty(message = "bos gecmeyiniz ")
	@Size(min = 11, max = 11)
	private String nationalIdentity;
}
