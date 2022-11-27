package com.kodlamaio.bootcampProject.business.concretes.users;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.users.BootcampService;
import com.kodlamaio.bootcampProject.business.abstracts.users.InstructorService;
import com.kodlamaio.bootcampProject.business.constants.BusinessMessages;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.bootcamp.CreateBootcampRequest;
import com.kodlamaio.bootcampProject.business.requests.bootcamp.UpdateBootcampRequest;
import com.kodlamaio.bootcampProject.business.responses.bootcamp.CreateBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamp.GetAllBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamp.GetBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamp.UpdateBootcampResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.BootcampRepository;
import com.kodlamaio.bootcampProject.entities.Bootcamp;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BootcampManager implements BootcampService {
	private BootcampRepository bootcampRepository;
	private ModelMapperService modelMapperService;
	private InstructorService instructorService;

	@Override
	public Result delete(int id) {
		this.checkIfBootcampExistsById(id);
		this.bootcampRepository.deleteById(id);
		return new SuccessResult(Messages.BootcampDeleted);
	}

	@Override
	public DataResult<List<GetAllBootcampResponse>> getAll() {
		List<Bootcamp> bootcamps = this.bootcampRepository.findAll();

		List<GetAllBootcampResponse> bootcampResponse = bootcamps.stream()
				.map(bootcamp -> this.modelMapperService.forResponse().map(bootcamp, GetAllBootcampResponse.class))
				.toList();

		return new SuccessDataResult<List<GetAllBootcampResponse>>(bootcampResponse);

	}

	@Override
	public DataResult<CreateBootcampResponse> add(CreateBootcampRequest createBootcampRequest) {
		checkIfInstructorExist(createBootcampRequest.getInstructorId());
		Bootcamp bootcamp = this.modelMapperService.forRequest().map(createBootcampRequest, Bootcamp.class);
		this.bootcampRepository.save(bootcamp);

		CreateBootcampResponse bootcampResponse = this.modelMapperService.forResponse().map(bootcamp,
				CreateBootcampResponse.class);
		return new SuccessDataResult<CreateBootcampResponse>(bootcampResponse, Messages.BootcampCreate);
	}

	@Override
	public DataResult<UpdateBootcampResponse> update(UpdateBootcampRequest updateBootcampRequest) {
		this.checkIfBootcampExistsById(updateBootcampRequest.getId());
		this.checkIfInstructorExist(updateBootcampRequest.getInstructorId());
		Bootcamp bootcamp = this.modelMapperService.forRequest().map(updateBootcampRequest, Bootcamp.class);
		this.bootcampRepository.save(bootcamp);

		UpdateBootcampResponse bootcampResponse = this.modelMapperService.forResponse().map(bootcamp,
				UpdateBootcampResponse.class);
		return new SuccessDataResult<UpdateBootcampResponse>(bootcampResponse, Messages.BootcampUpdate);
	}

	@Override
	public DataResult<GetBootcampResponse> getById(int id) {
		this.checkIfBootcampExistsById(id);
		Bootcamp bootcamp = this.bootcampRepository.findById(id);
		GetBootcampResponse bootcampResponses = this.modelMapperService.forResponse().map(bootcamp,
				GetBootcampResponse.class);

		return new SuccessDataResult<GetBootcampResponse>(bootcampResponses);
	}



	public void checkIfBootcampExistsById(int id) {
		Bootcamp bootcamp = this.bootcampRepository.findById(id);
		if (bootcamp == null) {
			throw new BusinessException(BusinessMessages.BootcampNoExists);
		}
	}
	
	public void checkIfInstructorExist(int instructorId) {
		var result=instructorService.getById(instructorId);
		if(result==null) {
			throw new BusinessException(BusinessMessages.InstructorNoExists);
		}
	}




}