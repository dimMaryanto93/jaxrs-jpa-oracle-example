package com.maryanto.dimas.example.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.maryanto.dimas.example.entity.Nasabah;

@Path("/nasabah")
public class NasabahController {

	EntityManager entityManager;

	public NasabahController() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
		this.entityManager = emf.createEntityManager();
	}

	@GET
	@Path("/value")
	@Produces(MediaType.APPLICATION_JSON)
	public String getNasabah() {
		return "{ \"nama\" : \"Dimas Maryanto\" }";
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Nasabah> findAll() {
		Query query =  entityManager.createQuery("select n from Nasabah as n");
		return query.getResultList();
	}

}
