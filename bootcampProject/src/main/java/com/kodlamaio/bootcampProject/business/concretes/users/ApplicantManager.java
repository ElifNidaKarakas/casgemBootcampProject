package com.kodlamaio.bootcampProject.business.concretes.users;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.This;
import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.users.ApplicantService;
import com.kodlamaio.bootcampProject.business.abstracts.users.ApplicationService;
import com.kodlamaio.bootcampProject.business.abstracts.users.BlackListService;
import com.kodlamaio.bootcampProject.business.abstracts.users.BootcampService;
import com.kodlamaio.bootcampProject.business.constants.BusinessMessages;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.applicant.CreateApplicantRequest;
import com.kodlamaio.bootcampProject.business.requests.applicant.DeleteApplicantRequest;
import com.kodlamaio.bootcampProject.business.requests.applicant.UpdateApplicantRequest;
import com.kodlamaio.bootcampProject.business.responses.applicant.CreateApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.applicant.DeleteApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.applicant.GetAllApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.applicant.GetApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.applicant.UpdateApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.application.GetApplicationResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.ApplicantRepository;
import com.kodlamaio.bootcampProject.entities.applications.Application;
import com.kodlamaio.bootcampProject.entities.users.Applicant;
import com.kodlamaio.bootcampProject.entities.users.Instructor;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ApplicantManager implements ApplicantService {
	private ApplicantRepository applicantRepository;
	private ModelMapperService modelMapperService;

	@Override
	public Result delete(int id) {
		this.checkIfApplicantExistsById(id);
		this.applicantRepository.deleteById(id);
		return new SuccessResult(Messages.InstructorDeleted);
	}

	@Override
	public DataResult<List<GetAllApplicantResponse>> getAll() {
		List<Applicant> applicants = this.applicantRepository.findAll();
		List<GetAllApplicantResponse> applicantResponses = applicants.stream()
				.map(applicant -> this.modelMapperService.forResponse().map(applicant, GetAllApplicantResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllApplicantResponse>>(applicantResponses, Messages.InstructorCreate);
	}

	@Override
	public DataResult<CreateApplicantResponse> add(CreateApplicantRequest createApplicantRequest) {
		this.checkIfApplicantExistsNationalIdentity(createApplicantRequest.getNationalIdentity());

		Applicant applicant = this.modelMapperService.forRequest().map(createApplicantRequest, Applicant.class);
		this.applicantRepository.save(applicant);

		CreateApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				CreateApplicantResponse.class);
		return new SuccessDataResult<CreateApplicantResponse>(applicantResponse, Messages.InstructorCreate);
	}

	@Override
	public DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest updateApplicantRequest) {
		this.checkIfApplicantExistsById(updateApplicantRequest.getId());
		Applicant applicant = this.modelMapperService.forRequest().map(updateApplicantRequest, Applicant.class);
		this.applicantRepository.save(applicant);

		UpdateApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				UpdateApplicantResponse.class);
		return new SuccessDataResult<UpdateApplicantResponse>(applicantResponse, Messages.InstructorUpdate);
	}

	@Override
	public DataResult<GetApplicantResponse> getById(int id) {
		this.checkIfApplicantExistsById(id);
		Applicant applicant = this.applicantRepository.findById(id);

		GetApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				GetApplicantResponse.class);
		return new SuccessDataResult<GetApplicantResponse>(applicantResponse, Messages.ApplicantCreate);

	}

	public void checkIfApplicantExistsNationalIdentity(String nationalIdentity) {
		Applicant applicant = this.applicantRepository.findByNationalIdentity(nationalIdentity);
		if (applicant != null) {
			throw new BusinessException(BusinessMessages.ApplicantExists);
		}

	}

	public void checkIfApplicantExistsById(int id) {
		Applicant applicant = this.applicantRepository.findById(id);
		if (applicant != null) {
			throw new BusinessException(BusinessMessages.ApplicantNoExists);
		}
	}

}
