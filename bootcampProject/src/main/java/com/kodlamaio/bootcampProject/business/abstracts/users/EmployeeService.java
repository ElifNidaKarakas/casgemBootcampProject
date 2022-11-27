package com.kodlamaio.bootcampProject.business.abstracts.users;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.employee.CreateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.requests.employee.UpdateEmployessRequest;
import com.kodlamaio.bootcampProject.business.responses.employee.CreateEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.employee.GetAllEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.employee.GetEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.employee.UpdateEmployeeResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

public interface EmployeeService   {
//	List<GetAllEmployeeResponse> getAll();
//	CreateEmployeeResponse add(CreateEmployeeRequest createEmployeeRequest);
//	UpdateEmployeeResponse update(UpdateApplicantRequest updateApplicantRequest);
//	

	Result delete(int id);
	DataResult<List<GetAllEmployeeResponse>> getAll();
	DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest createEmployeeRequest);
	DataResult<UpdateEmployeeResponse> update(UpdateEmployessRequest updateEmployessRequest);
	DataResult<GetEmployeeResponse> getById(int id);
	
}
