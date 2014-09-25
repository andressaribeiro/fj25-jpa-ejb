package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

/**
 * 
 * @author Andressa Albuquerque
 *
 */
public class TestaPesquisaIdConta {

	public static void main(String[] args) {
		EntityManager manager = JPAUtil.getEntityManager();

		ContaDao dao = new ContaDao(manager);

		Conta encontrado = dao.busca(1);

		System.out.println("Titular da conta encontrada: "
				+ encontrado.getTitular());
		
		manager.close();
	}

}
