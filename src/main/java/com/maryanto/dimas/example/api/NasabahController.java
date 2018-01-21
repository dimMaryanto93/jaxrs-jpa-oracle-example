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

    EntityManager entityManager;

    public NasabahController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        this.entityManager = emf.createEntityManager();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Nasabah getNasabah(@PathParam("id") String id) {
        return (Nasabah) this.entityManager.createQuery("select n from Nasabah n where n.cif = :id")
                .setParameter("id", id)
                .getSingleResult();
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

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Nasabah save(Nasabah nasabah) {

        entityManager.getTransaction().begin();
        entityManager.persist(nasabah);
        entityManager.getTransaction().commit();
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

        this.entityManager.getTransaction().begin();
        nasabah = this.entityManager.merge(nasabah);
        this.entityManager.getTransaction().commit();
        return nasabah;

    }

    @DELETE
    @Path("/remove/{removeParam}")
    @Produces(MediaType.TEXT_PLAIN)
    public String remove(@PathParam("removeParam") String id) {

        this.entityManager.getTransaction().begin();
        Nasabah nasabah = 
                (Nasabah) this.entityManager.createQuery("select n from Nasabah n where n.cif = :param")
                        .setParameter("param", id)
                        .getSingleResult();
        if (nasabah != null) {
            this.entityManager.remove(nasabah);
            this.entityManager.getTransaction().commit();
            return "Berhasil dihapus";
        } else {
            return "Tidak ada data yang dihapus";
        }
    }
}
