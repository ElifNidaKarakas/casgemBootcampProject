package com.kodlamaio.bootcampProject.business.abstracts.users;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.blackList.CreateBlackListRequest;
import com.kodlamaio.bootcampProject.business.requests.blackList.UpdateBlackListRequest;
import com.kodlamaio.bootcampProject.business.responses.blackList.CreateBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blackList.GetAllBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blackList.GetBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blackList.UpdateBlackListResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

public interface BlackListService {
Result delete(int id);
DataResult<List<GetAllBlackListResponse>> getAll();
DataResult<CreateBlackListResponse> add(CreateBlackListRequest createBlackListRequest);
DataResult<UpdateBlackListResponse> update(UpdateBlackListRequest updateBlackListRequest);
DataResult<GetBlackListResponse> getById(int id);
DataResult<GetBlackListResponse> getByApplicantId(int applicantId);

}
