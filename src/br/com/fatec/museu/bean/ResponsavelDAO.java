package br.com.fatec.museu.bean;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fatec.museu.dao.JPAUtil;
import br.com.fatec.museu.modelo.Responsavel;

public class ResponsavelDAO {
	
	private EntityManager em;

	public ResponsavelDAO (){
		this.em = JPAUtil.getEntityManager();
	}

	public void adiciona(Responsavel responsavel) {
		em.getTransaction().begin();
		em.persist(responsavel);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(Responsavel responsavel) {
		em.getTransaction().begin();
		em.remove(em.merge(responsavel));
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(Responsavel responsavel) {
		em.getTransaction().begin();
		em.merge(responsavel);
		em.getTransaction().commit();
		em.close();
	}

	public List<Responsavel> listaTodos() {
		List<Responsavel> lista = em.createQuery("select o from Responsavel o order by o.nome", Responsavel.class).getResultList();
		em.close();
		return lista;
	}

	public Responsavel buscaPorId(Integer id) {
		Responsavel responsavel = em.find(Responsavel.class, id);
		em.close();
		return responsavel;
	}
}
