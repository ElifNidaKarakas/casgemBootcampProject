package com.kodlamaio.bootcampProject.business.requests.blackList;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBlackListRequest {
	private int id;

	@NotBlank
	private int applicantId;
	
	@NotBlank
	private LocalDate dateAdded;
	
	@NotBlank
	@Length(min=5,max=50)
	private String reason;
	

}
