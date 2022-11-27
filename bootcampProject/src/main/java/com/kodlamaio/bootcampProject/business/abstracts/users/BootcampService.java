package com.kodlamaio.bootcampProject.business.abstracts.users;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.bootcamp.CreateBootcampRequest;
import com.kodlamaio.bootcampProject.business.requests.bootcamp.UpdateBootcampRequest;
import com.kodlamaio.bootcampProject.business.responses.bootcamp.CreateBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamp.GetAllBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamp.GetBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamp.UpdateBootcampResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

public interface BootcampService {
Result delete(int id);
DataResult<List<GetAllBootcampResponse>> getAll();
DataResult<GetBootcampResponse> getById(int id);
DataResult<CreateBootcampResponse> add(CreateBootcampRequest createBootcampRequest);
DataResult<UpdateBootcampResponse> update (UpdateBootcampRequest updateBootcampRequest);

}
