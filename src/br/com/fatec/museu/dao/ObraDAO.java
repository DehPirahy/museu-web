package br.com.fatec.museu.dao;

import java.util.List;

import javax.persistence.EntityManager;

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
		List<Obra> lista = em.createQuery("select o from Obra o order by o.nome", Obra.class).getResultList();
		em.close();
		return lista;
	}

	public Obra buscaPorId(Integer id) {
		Obra obra = em.find(Obra.class, id);
		em.close();
		return obra;
	}
}
