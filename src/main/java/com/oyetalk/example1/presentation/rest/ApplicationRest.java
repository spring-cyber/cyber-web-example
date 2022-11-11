package com.oyetalk.example1.presentation.rest;

import com.cyber.domain.entity.DataResponse;
import com.cyber.domain.entity.IdRequest;
import com.cyber.domain.entity.PagingData;
import com.cyber.domain.entity.Response;
import com.oyetalk.example1.domain.entity.Application;
import com.oyetalk.example1.application.ApplicationService;
import com.oyetalk.example1.domain.request.ApplicationRequest;
import com.oyetalk.example1.domain.request.CreateApplicationRequest;
import com.oyetalk.example1.domain.request.UpdateApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.Valid;
import java.util.Date;

@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ApplicationRest {

	@Autowired
	ApplicationService applicationService;

	@GetMapping("/application/search")
	public Response searchApplication(@Valid ApplicationRequest request) {
		DataResponse<PagingData<Application>> response = new DataResponse<PagingData<Application>>();
        Application  application = request.toEvent();
		PagingData<Application> applicationPage = applicationService.selectPage(application);
		response.setData(applicationPage);
		return response;
	}
	
	@PostMapping("/application")
	public Response saveApplication(@RequestBody @Valid CreateApplicationRequest request) {
		String openId = "";
	    Application  application = request.toEvent(openId);

		applicationService.save(application);
		return new Response();
	}

	@PutMapping("/application")
	public Response updateApplication(@RequestBody @Valid UpdateApplicationRequest request) {
		String openId = "";
	    Application  application = request.toEvent(openId);
		applicationService.updateById(application);
		return new Response();
	}

	@DeleteMapping("/application")
	public Response deleteApplication(@Valid IdRequest requestId) {
		String openId = "";

		Application application = new Application();
		application.setId(requestId.getId());
		application.setUpdatorId(openId);
        application.setUpdateTime(new Date());

		applicationService.deleteById(application);
		return new Response();
	}
}
