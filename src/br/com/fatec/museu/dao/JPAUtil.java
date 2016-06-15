package br.com.fatec.museu.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = null;

	public static EntityManager getEntityManager() {
		if (emf == null){
			emf = Persistence.createEntityManagerFactory("museu");
		}
		return emf.createEntityManager();
	}
}