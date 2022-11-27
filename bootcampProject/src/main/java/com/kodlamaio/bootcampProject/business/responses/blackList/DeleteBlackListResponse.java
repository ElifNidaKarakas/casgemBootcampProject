package com.kodlamaio.bootcampProject.business.responses.blackList;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBlackListResponse {
	private int id;

	//private int userId;

	private LocalDate dateAdded;

	private String reason;
	
	private int applicantId;
}
