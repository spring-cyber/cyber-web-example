package com.oyetalk.iot.presentation.rest;

import com.cyber.domain.entity.PagingResponse;
import com.cyber.domain.entity.Response;
import com.oyetalk.iot.domain.entity.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class AddressRest {

	@Value("${server.port}")
	String port;

	@GetMapping("/address/search")
	public Response searchAddress() {
		PagingResponse<Address> response = new PagingResponse<Address>();
		List<Address> list = new ArrayList<>();
		Address address1 = new Address();
		address1.setAddressId("0001");
		address1.setAddressName(port);
		list.add(address1);

		Address address2 = new Address();
		address2.setAddressId("0002");
		address2.setAddressName(port);
		list.add(address2);

		response.setRow(list.size());
		response.setData(list);
		return response;
	}
}
