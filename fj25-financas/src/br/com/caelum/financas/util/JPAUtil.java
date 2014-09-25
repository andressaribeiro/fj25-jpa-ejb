package br.com.caelum.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author Andressa Albuquerque
 *
 */
public class JPAUtil {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("controlefinancas");
	
	public static EntityManager getEntityManager(){
		return factory.createEntityManager();
	}


}
