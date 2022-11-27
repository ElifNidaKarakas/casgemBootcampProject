package com.kodlamaio.bootcampProject.business.concretes.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.users.EmployeeService;
import com.kodlamaio.bootcampProject.business.constants.BusinessMessages;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.employee.CreateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.requests.employee.UpdateEmployessRequest;
import com.kodlamaio.bootcampProject.business.responses.employee.CreateEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.employee.GetAllEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.employee.GetEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.employee.UpdateEmployeeResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.EmployeeRepository;
import com.kodlamaio.bootcampProject.entities.users.Employee;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {
	private EmployeeRepository employeeRepository;
	private ModelMapperService modelMapperService;


		
	@Override
	public Result delete(int id) {
	   this.checkIfEmployeeExistById(id);
		this.employeeRepository.deleteById(id);
		return new SuccessResult(Messages.EmployeeDeleted);
	}

	@Override
	public DataResult<List<GetAllEmployeeResponse>> getAll() {
		List<Employee> employees = this.employeeRepository.findAll();
		List<GetAllEmployeeResponse> responseItem = employees.stream()
				.map(employee -> this.modelMapperService.forResponse().map(employee, GetAllEmployeeResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllEmployeeResponse>>(responseItem);
	}

	@Override
	public DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest createEmployeeRequest) {
		this.CheckIfEmployeeExistsNationalIdentity(createEmployeeRequest.getNationalIdentity());
		Employee employee = this.modelMapperService.forRequest().map(createEmployeeRequest, Employee.class);
		employeeRepository.save(employee);

		CreateEmployeeResponse employeResult = this.modelMapperService.forResponse().map(employee,
				CreateEmployeeResponse.class);
		return new SuccessDataResult<CreateEmployeeResponse>(employeResult, Messages.EmployeeCreate);
	}

	@Override
	public DataResult<UpdateEmployeeResponse> update(UpdateEmployessRequest updateEmployessRequest) {
		this.checkIfEmployeeExistById(updateEmployessRequest.getId());
		Employee employee = this.modelMapperService.forRequest().map(updateEmployessRequest, Employee.class);
		this.employeeRepository.save(employee);

		UpdateEmployeeResponse updateEmployeeResponse = this.modelMapperService.forResponse().map(employee,
				UpdateEmployeeResponse.class);
		return new SuccessDataResult<UpdateEmployeeResponse>(updateEmployeeResponse, Messages.EmployeeUpdate);
	}

	@Override
	public DataResult<GetEmployeeResponse> getById(int id) {
		this.checkIfEmployeeExistById(id);
		Employee employee = this.employeeRepository.findById(id);

		GetEmployeeResponse employeeResponse = this.modelMapperService.forResponse().map(employee,
				GetEmployeeResponse.class);
		return new SuccessDataResult<GetEmployeeResponse>(employeeResponse);
	}

	public void CheckIfEmployeeExistsNationalIdentity(String nationalIdentity) {
		Employee employee=this.employeeRepository.findByNationalIdentity(nationalIdentity);
		if(employee!=null) {
			throw new BusinessException(BusinessMessages.EmployeeExists);
		}
	}
	
	public void checkIfEmployeeExistById(int id) {
		Employee employee=this.employeeRepository.findById(id);
		if(employee!=null) {
			throw new BusinessException(BusinessMessages.EmployeeNoExists);
		}
	}
	/*
	 * @Override public List<GetAllEmployeeResponse> getAll() { List<Employee>
	 * employees=employeeRepository.findAll(); List<GetAllEmployeeResponse>
	 * employeeResponses=new ArrayList<GetAllEmployeeResponse>();
	 * 
	 * for(Employee employee:employees) { GetAllEmployeeResponse response=new
	 * GetAllEmployeeResponse(); response.setPosition(employee.getPosition());
	 * employeeResponses.add(response);
	 * 
	 * } return employeeResponses; }
	 * 
	 * @Override public CreateEmployeeResponse add(CreateEmployeeRequest
	 * createEmployeeRequest) { Employee
	 * employee=this.modelMapperService.forRequest().map(createEmployeeRequest,
	 * Employee.class); employeeRepository.save(employee);
	 * 
	 * CreateEmployeeResponse
	 * employeeResponse=this.modelMapperService.forResponse().map(employee,
	 * CreateEmployeeResponse.class);
	 * 
	 * return employeeResponse; }
	 * 
	 * @Override public UpdateEmployeeResponse update(UpdateApplicantRequest
	 * updateApplicantRequest) { Employee
	 * employee=this.modelMapperService.forRequest().map(updateApplicantRequest,
	 * Employee.class); employeeRepository.save(employee);
	 * 
	 * UpdateEmployeeResponse
	 * employeeResponse=this.modelMapperService.forResponse().map(employee,
	 * UpdateEmployeeResponse.class); return employeeResponse; }
	 */

}
