package com.kodlamaio.bootcampProject.business.requests.applicant;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicantRequest {

	private int id;

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
	@NotEmpty
	@Length(min = 5, max = 50)
	private String about;
	@NotNull
	@NotEmpty(message = "bos gecmeyiniz")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern ="dd/MM/yyyy")
	private LocalDate dateOfBirth;

	@NotNull
	@NotEmpty(message = "bos gecmeyiniz ")
	@Size(min = 11, max = 11)
	private String nationalIdentity;
}