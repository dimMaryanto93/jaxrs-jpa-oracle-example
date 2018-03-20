package com.maryanto.dimas.example.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.maryanto.dimas.example.entity.Nasabah;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

@Path("/nasabah")
public class NasabahController {

	private final static String PERSISTANCE_UNIT = "persistenceUnit";

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Nasabah getNasabah(@PathParam("id") String id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager em = emf.createEntityManager();
		Nasabah nasabah = (Nasabah) em.createQuery("select n from Nasabah n where n.cif = :id").setParameter("id", id)
				.getSingleResult();
		em.close();
		emf.close();
		return nasabah;
	}

	@GET
	@Path("/listByNama")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Nasabah> findNasabah(@QueryParam("nama") String nama) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("select n from Nasabah n where n.namaLengkap = :namaLengkap");
		query.setParameter("namaLengkap", nama);
		List<Nasabah> list = query.getResultList();
		em.close();
		emf.close();
		return list;
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Nasabah> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("select n from Nasabah as n");
		List<Nasabah> list = query.getResultList();
		em.close();
		emf.close();
		return list;
	}

	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Nasabah save(Nasabah nasabah) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(nasabah);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return nasabah;
	}

	/**
	 * jersey not supported
	 *
	 * @param nasabah
	 * @return
	 */
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Nasabah update(Nasabah nasabah) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		nasabah = em.merge(nasabah);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return nasabah;

	}

	@DELETE
	@Path("/remove/{removeParam}")
	@Produces(MediaType.TEXT_PLAIN)
	public String remove(@PathParam("removeParam") String id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager em = emf.createEntityManager();
		
		Nasabah nasabah = (Nasabah) em.createQuery("select n from Nasabah n where n.cif = :param")
				.setParameter("param", id).getSingleResult();
		
		if (nasabah != null) {
			em.getTransaction().begin();
			em.remove(nasabah);
			em.getTransaction().commit();
			em.close();
			emf.close();
			return "Berhasil dihapus";
		} else {
			return "Tidak ada data yang dihapus";
		}
	}
}
