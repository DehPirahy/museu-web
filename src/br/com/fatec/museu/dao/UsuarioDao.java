package br.com.fatec.museu.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fatec.museu.modelo.Usuario;

public class UsuarioDao {

	public boolean existe(Usuario usuario) {
		
		new JPAUtil();
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Usuario> query = em.createQuery(
				  " select u from Usuario u "
				+ " where u.email = :email and u.senha = :senha", Usuario.class);
		
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		try {
			query.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		
		em.close();
		
		return true;
	}

}
