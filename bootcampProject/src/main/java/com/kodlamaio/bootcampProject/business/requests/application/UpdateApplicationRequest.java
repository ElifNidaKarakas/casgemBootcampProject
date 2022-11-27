package com.kodlamaio.bootcampProject.business.requests.application;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicationRequest {
	private int id;

	@Min(value = 1)
	private int bootcampId;
	@Min(value = 1)
	private int applicantId;
	@Min(value = 1)
	@Max(value = 4)
	private int state;
}
