package com.kodlamaio.bootcampProject.business.concretes.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.users.InstructorService;
import com.kodlamaio.bootcampProject.business.constants.BusinessMessages;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.instructor.CreateInstructorRequest;
import com.kodlamaio.bootcampProject.business.requests.instructor.UpdateInstructorRequest;
import com.kodlamaio.bootcampProject.business.responses.instructor.CreateInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.instructor.GetAllInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.instructor.GetInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.instructor.UpdateInstructorResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.InstructorRepository;
import com.kodlamaio.bootcampProject.entities.users.Instructor;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class InstructorManager implements InstructorService {
	private InstructorRepository instructorRepository;
	private ModelMapperService modelMapperService;

	@Override
	public Result delete(int id) {
		this.checkIfInstructorExistById(id);
		this.instructorRepository.deleteById(id);
		return new SuccessResult(Messages.InstructorDeleted);
	}
	@Override
	public DataResult<CreateInstructorResponse> add(CreateInstructorRequest createInstructorRequest) {
		this.checkIfInstructorExistByNationalIdentity(createInstructorRequest.getNationalIdentity());
		Instructor instructor=this.modelMapperService.forRequest().map(createInstructorRequest, Instructor.class);
		this.instructorRepository.save(instructor);
		
		CreateInstructorResponse response=this.modelMapperService.forResponse().map(instructor, CreateInstructorResponse.class);
		return new SuccessDataResult<CreateInstructorResponse>(response,Messages.InstructorCreate) ;
	}
	
	
	@Override
	public DataResult<List<GetAllInstructorResponse>> getAll() {
		List<Instructor> instructors=this.instructorRepository.findAll();
		
		List<GetAllInstructorResponse> instructorResponses=instructors.stream().
				map(instructor->this.modelMapperService.forResponse().map(instructor, GetAllInstructorResponse.class)).
				collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllInstructorResponse>>(instructorResponses);
	}
	
	
	@Override
	public DataResult<UpdateInstructorResponse> update(UpdateInstructorRequest updateInstructorRequest) {
		this.checkIfInstructorExistById(updateInstructorRequest.getId());
		this.checkIfInstructorExistByNationalIdentity(updateInstructorRequest.getNationalIdentity());
		Instructor instructor=this.modelMapperService.forRequest().map(updateInstructorRequest, Instructor.class);
		this.instructorRepository.save(instructor);
		
		UpdateInstructorResponse response=this.modelMapperService.forResponse().map(instructor, UpdateInstructorResponse.class);
		return new SuccessDataResult<UpdateInstructorResponse>(response,Messages.InstructorUpdate);
	}
	
	
	@Override
	public DataResult<GetInstructorResponse> getById(int id) {
		this.checkIfInstructorExistById(id);
		Instructor instructor=this.instructorRepository.findById(id);
		
		GetInstructorResponse response=this.modelMapperService.forResponse().map(instructor, GetInstructorResponse.class);
		return new SuccessDataResult<GetInstructorResponse>(response);
	}
	
	 void checkIfInstructorExistByNationalIdentity(String nationalIdentity) {
		Instructor instructor=this.instructorRepository.findByNationalIdentity(nationalIdentity);
		if(instructor!=null) {
			throw new BusinessException(BusinessMessages.InstructorExists);
		}
	}

    private void checkIfInstructorExistById(int id) {
    	Instructor instructor=this.instructorRepository.findById(id);
    	if(instructor!=null) {
    		throw new BusinessException(BusinessMessages.InstructorNoExists);
    	}
    	
    }
//	@Override
//	public List<GetAllInstructorResponse> getAll() {
//		List<Instructor> instructors=instructorRepository.findAll();
//		List<GetAllInstructorResponse> instructorResponses=new ArrayList<GetAllInstructorResponse>();
//		
//		for (Instructor instructor : instructors) {
//			GetAllInstructorResponse instructorItem=modelMapperService.forResponse().map(instructor, GetAllInstructorResponse.class);
//			instructorResponses.add(instructorItem);
//			
//			
//			
//		}
//		return instructorResponses;
//	}
//
//	@Override
//	public CreateInstructorResponse add(CreateInstructorRequest createInstructorRequest) {
//		Instructor instructor=this.modelMapperService.forRequest().map(createInstructorRequest, Instructor.class);
//		instructorRepository.save(instructor);
//		
//		CreateInstructorResponse instructorResponse=this.modelMapperService.forResponse().map(instructor, CreateInstructorResponse.class);
//		return instructorResponse;
//	}
//
//	@Override
//	public DeleteInstructorResponse delete(DeleteInstructorRequest deleteInstructorRequest) {
//		Instructor instructor=this.instructorRepository.findById(deleteInstructorRequest.getId()).get();
//		instructorRepository.delete(instructor);
//		
//		DeleteInstructorResponse instructorResponse=this.modelMapperService.forResponse().map(instructor, DeleteInstructorResponse.class);
//		return instructorResponse;
//		
//	}
//
//	@Override
//	public UpdateInstructorResponse update(UpdateInstructorRequest updateInstructorRequest) {
//		Instructor instructor=this.modelMapperService.forRequest().map(updateInstructorRequest, Instructor.class);
//	
//		instructorRepository.save(instructor);
//		
//		UpdateInstructorResponse instructorResponse=this.modelMapperService.forResponse().map(instructor, UpdateInstructorResponse.class);
//		
//		return instructorResponse;
//	}

}
