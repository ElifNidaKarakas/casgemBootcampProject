package com.kodlamaio.bootcampProject.business.concretes.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.users.ApplicantService;
import com.kodlamaio.bootcampProject.business.abstracts.users.ApplicationService;
import com.kodlamaio.bootcampProject.business.abstracts.users.BlackListService;
import com.kodlamaio.bootcampProject.business.abstracts.users.BootcampService;
import com.kodlamaio.bootcampProject.business.constants.BusinessMessages;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.application.CreateApplicationRequest;
import com.kodlamaio.bootcampProject.business.requests.application.UpdateApplicationRequest;
import com.kodlamaio.bootcampProject.business.responses.application.CreateApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.application.GetAllApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.application.GetApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.application.UpdateApplicationResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.ApplicationRepository;
import com.kodlamaio.bootcampProject.entities.applications.Application;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ApplicationManager implements ApplicationService{
	
	private ApplicationRepository applicationRepository;
	private ModelMapperService modelMapperService;
	private BlackListService blackListService;
	private BootcampService bootcampService;
	private ApplicantService applicantService;
	
	@Override
	public Result delete(int id) {
		this.checkIfApplicationExistsId(id);
		this.applicationRepository.deleteById(id);
		return new SuccessResult(Messages.ApplicationDeleted);
	}
	@Override
	public DataResult<List<GetAllApplicationResponse>> getAll() {
		List<Application>applications=this.applicationRepository.findAll();
		List<GetAllApplicationResponse> applicationResponse=applications.stream().
				map(application->this.modelMapperService.forResponse()
				.map(application, GetAllApplicationResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllApplicationResponse>>(applicationResponse);
		
		
	}
	@Override
	public DataResult<CreateApplicationResponse> add(CreateApplicationRequest createApplicationRequest) {
		checkIfApplicantExist(createApplicationRequest.getApplicantId());
		checkIfBootcampExists(createApplicationRequest.getBootcampId());
		checkIfApplicationExistsByBlackList(createApplicationRequest.getApplicantId());
	    
		Application application=this.modelMapperService.forRequest().map(createApplicationRequest, Application.class);
		this.applicationRepository.save(application);
		
		CreateApplicationResponse  applicationResponse=this.modelMapperService.forResponse().map(application, CreateApplicationResponse.class);
		return new SuccessDataResult<CreateApplicationResponse>(applicationResponse, Messages.ApplicationCreate);
	}
	@Override
	public DataResult<UpdateApplicationResponse> update(UpdateApplicationRequest updateApplicationRequest) {
		this.checkIfApplicationExistsId(updateApplicationRequest.getId());
		checkIfApplicantExist(updateApplicationRequest.getApplicantId());
		checkIfBootcampExists(updateApplicationRequest.getBootcampId());
		checkIfApplicationExistsByBlackList(updateApplicationRequest.getApplicantId());
		Application application=this.modelMapperService.forRequest().map(updateApplicationRequest, Application.class);
		this.applicationRepository.save(application);
		
		UpdateApplicationResponse applicationResponse=this.modelMapperService.forResponse().map(application, UpdateApplicationResponse.class);
		return new SuccessDataResult<UpdateApplicationResponse>(applicationResponse,Messages.ApplicationUpdate);
	}
	@Override
	public DataResult<GetApplicationResponse> getById(int id) {
		this.checkIfApplicationExistsId(id);
		Application application=this.applicationRepository.findById(id);
		
		GetApplicationResponse applicationResponse=this.modelMapperService.forResponse().map(application, GetApplicationResponse.class);
		return new SuccessDataResult<GetApplicationResponse>(applicationResponse);
	}
	
	public void checkIfApplicationExistsId(int id) {
		Application application=this.applicationRepository.findById(id);
		if(application==null) {
			throw new BusinessException(BusinessMessages.ApplicationNoExists);
		}
	}
	
	
	private void checkIfApplicationExistsByBlackList(int id) {
		var result = blackListService.getByApplicantId(id);
		if (result != null) {
			throw new BusinessException(BusinessMessages.InBlackList);
		}
	}
	
	
	private void checkIfApplicantExist(int applicantId) {
		var result =applicantService.getById(applicantId);
		if(result==null) {
			throw new BusinessException(BusinessMessages.ApplicantNoExists);
		}
	}
	
	
	private void checkIfBootcampExists(int bootcampId) {
		var result=bootcampService.getById(bootcampId);
		if(result==null) {
			throw new BusinessException(BusinessMessages.BootcampNoExists);
		}
	}
}
