package com.kodlamaio.bootcampProject.business.responses.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicationResponse {
	
	private int userId;
	private int bootcampId;

	private int state;
}
