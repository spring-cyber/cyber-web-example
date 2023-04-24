package com.oyetalk.iot.presentation.rest;

import com.cyber.application.controller.AuthingTokenController;
import com.cyber.domain.entity.DataResponse;
import com.cyber.domain.entity.IdRequest;
import com.cyber.domain.entity.PagingData;
import com.cyber.domain.entity.Response;
import com.oyetalk.iot.application.IotService;
import com.oyetalk.iot.domain.entity.Iot;
import com.oyetalk.iot.domain.request.CreateIotRequest;
import com.oyetalk.iot.domain.request.IotRequest;
import com.oyetalk.iot.domain.request.UpdateIotRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class IotRest extends AuthingTokenController {

	@Autowired
	IotService iotService;

	@GetMapping("/iot/search")
	public Response searchIot(@Valid IotRequest request) {
		DataResponse<PagingData<List<Iot>>> response = new DataResponse<>();
        Iot  iot = request.toEvent();
		PagingData<List<Iot>> iotPage = iotService.selectPage(iot);
		response.setData(iotPage);
		return response;
	}

	@PostMapping("/iot")
	public Response saveIot(@RequestBody @Valid CreateIotRequest request) {
	    Iot  iot = request.toEvent(getSessionId());

		iotService.save(iot);
		return new Response();
	}

	@PutMapping("/iot")
	public Response updateIot(@RequestBody @Valid UpdateIotRequest request) {
	    Iot  iot = request.toEvent(getSessionId());
		iotService.updateById(iot);
		return new Response();
	}

	@DeleteMapping("/iot")
	public Response deleteIot(@Valid IdRequest requestId) {
		Iot iot = new Iot();
		iot.setId(requestId.getId());

		iot.setUpdatorId(getSessionId());
        iot.setUpdateTime(new Date());

		iotService.deleteById(iot);
		return new Response();
	}
}
