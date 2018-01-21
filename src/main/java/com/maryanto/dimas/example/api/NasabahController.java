
package com.maryanto.dimas.example.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Nasabah getNasabah(@PathParam("id") Integer id) {
		return this.entityManager.find(Nasabah.class, id);
	}

	@GET
	@Path("/listByNama")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Nasabah> findNasabah(@QueryParam("nama") String nama) {

		Query query = this.entityManager.createQuery("select n from Nasabah n where n.namaLengkap = :namaLengkap");
		query.setParameter("namaLengkap", nama);
		return query.getResultList();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Nasabah> findAll() {

		Query query = entityManager.createQuery("select n from Nasabah as n");
		return query.getResultList();
	}

	// @Post
	// @Path("/save")
	// @Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	// public Nasabah save(Nasabah nasabah) {
	//
	// entityManager.getTransaction().begin();
	// entityManager.persist(nasabah);
	// entityManager.getTransaction().commit();
	// return nasabah;
	// }
	//
	// @PUT
	// @Path("/update")
	// @Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	// public Nasabah update(Nasabah nasabah) {
	//
	// this.entityManager.getTransaction().begin();
	// nasabah = this.entityManager.merge(nasabah);
	// this.entityManager.getTransaction().commit();
	// return nasabah;
	//
	// }
	//
	// @DELETE
	// @Path("/remove")
	// @Produces(MediaType.TEXT_PLAIN)
	// public String remove(@QueryParam("id") Integer id) {
	//
	// this.entityManager.getTransaction().begin();
	// this.entityManager.remove(this.getNasabah(id));
	// this.entityManager.getTransaction().commit();
	// return "Berhasil dihapus";
	// }

}
