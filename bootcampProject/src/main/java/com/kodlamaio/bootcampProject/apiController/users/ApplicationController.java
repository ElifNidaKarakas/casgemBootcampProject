package com.kodlamaio.bootcampProject.apiController.users;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.bootcampProject.business.abstracts.users.ApplicationService;
import com.kodlamaio.bootcampProject.business.requests.application.CreateApplicationRequest;
import com.kodlamaio.bootcampProject.business.requests.application.UpdateApplicationRequest;
import com.kodlamaio.bootcampProject.business.responses.application.CreateApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.application.GetAllApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.application.GetApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.application.UpdateApplicationResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@RequestMapping("/api/applications")
 @RestController
 @AllArgsConstructor
public class ApplicationController {
private ApplicationService applicationService;


@GetMapping("/{id}")
public DataResult<GetApplicationResponse> getById(@PathVariable int id){
	return this.applicationService.getById(id);
}

@GetMapping()
public DataResult<List<GetAllApplicationResponse>> getAll(){
	return applicationService.getAll();
}


@PostMapping
public DataResult<CreateApplicationResponse> add(@Valid@RequestBody CreateApplicationRequest createApplicationRequest) {
	return this.applicationService.add(createApplicationRequest);
}



@PutMapping
public DataResult<UpdateApplicationResponse> update(@Valid@RequestBody UpdateApplicationRequest updateApplicationRequest) {
	return this.applicationService.update(updateApplicationRequest);
	
}

@DeleteMapping("/{id}")
public Result delete(@PathVariable int id) {
	return this.applicationService.delete(id);
}

	
	
	
}