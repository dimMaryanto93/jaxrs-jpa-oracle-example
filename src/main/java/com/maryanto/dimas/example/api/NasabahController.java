package com.maryanto.dimas.example.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/nasabah")
public class NasabahController {
	
	@GET
	@Path("/value")
	@Produces(MediaType.APPLICATION_JSON)
	public String getNasabah() {
		return "{ \"nama\" : \"Dimas Maryanto\" }";
	}

}
