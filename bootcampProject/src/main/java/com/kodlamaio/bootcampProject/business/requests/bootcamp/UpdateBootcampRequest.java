package com.kodlamaio.bootcampProject.business.requests.bootcamp;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBootcampRequest {
	private int id;
	@NotNull(message = "bos gecmeyiniz")
	@Length(min = 3, max = 50)
	private String name;
	@NotNull(message = "bos gecmeyiniz")
	private LocalDate dateStart;
	@NotNull(message = "bos gecmeyiniz")
	private LocalDate dateEnd;
	@Min(value = 1, message = "State cannot be less than 1")
	@Max(value = 2, message = "State cannot be more than 2")
	@NotNull(message = "bos gecmeyiniz")
	private int state;
	@NotNull
	@NotEmpty
	private int instructorId;
}
