package br.com.fatec.museu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.fatec.museu.modelo.Obra;

public class ObraDAO {
	
	private EntityManager em;

	public ObraDAO (){
		this.em = JPAUtil.getEntityManager();
	}

	public void adiciona(Obra obra) {
		em.getTransaction().begin();
		em.persist(obra);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(Obra obra) {
		em.getTransaction().begin();
		em.remove(em.merge(obra));
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(Obra obra) {
		em.getTransaction().begin();
		em.merge(obra);
		em.getTransaction().commit();
		em.close();
	}

	public List<Obra> listaTodos() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Obra> query = builder.createQuery(Obra.class);
        Root<Obra> from = query.from(Obra.class);
        query.select(from).orderBy(builder.asc(from.get("nome")));
        List<Obra> lista = em.createQuery(query).getResultList();
        em.close();
		return lista;
	}
	
	
	

	public Obra buscaPorId(Integer id) {
		Obra obra = em.find(Obra.class, id);
		em.close();
		return obra;
	}
}
