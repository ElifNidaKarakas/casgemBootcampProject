package com.kodlamaio.bootcampProject.business.abstracts.users;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.applicant.CreateApplicantRequest;
import com.kodlamaio.bootcampProject.business.requests.applicant.UpdateApplicantRequest;
import com.kodlamaio.bootcampProject.business.responses.applicant.CreateApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.applicant.GetAllApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.applicant.GetApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.applicant.UpdateApplicantResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

public interface ApplicantService {
//	List<GetAllApplicantResponse> getAll();
//	CreateApplicantResponse add(CreateApplicantRequest createApplicantRequest);
//	UpdateApplicantResponse update(UpdateApplicantRequest updateApplicantRequest);
//	DeleteApplicantResponse delete(DeleteApplicantRequest deleteApplicantRequest);
//
//	
	Result delete(int id);
	DataResult<List<GetAllApplicantResponse>> getAll();
	DataResult<CreateApplicantResponse> add(CreateApplicantRequest createApplicantRequest);

	DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest updateApplicantRequest);
	
	DataResult<GetApplicantResponse> getById(int id);
	
	
//    Result removeAnApplicant(int id);
//    void checkIfApplicantExistById(int id);
}
