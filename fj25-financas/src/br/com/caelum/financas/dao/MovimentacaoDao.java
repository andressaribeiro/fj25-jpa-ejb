package br.com.caelum.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Movimentacao;

/**
 * 
 * @author Andressa Albuquerque
 *
 */
public class MovimentacaoDao {
	
	private EntityManager manager;

	public MovimentacaoDao(EntityManager manager) {
		this.manager = manager;
	}

	public Movimentacao busca(Integer id) {
		return this.manager.find(Movimentacao.class, id);
	}

	public List<Movimentacao> lista() {
		return this.manager.createQuery("select m from Movimentacao m", Movimentacao.class)
				.getResultList();
	}
	
	public void adiciona(Movimentacao movimentacao) {
		this.manager.persist(movimentacao);
	}
	
	public void remove(Movimentacao movimentacao) {
		this.manager.remove(movimentacao);
	}

}
