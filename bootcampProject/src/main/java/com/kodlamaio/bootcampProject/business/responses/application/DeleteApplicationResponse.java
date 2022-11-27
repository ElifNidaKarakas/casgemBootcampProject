package com.kodlamaio.bootcampProject.business.responses.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteApplicationResponse {
	private int id;
	private int applicantId;
	private int bootcampId;

	private int state;
}
