package br.com.caelum.financas.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.exception.ValorInvalidoException;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.modelo.ValorPorMesEAno;

@Stateless
public class MovimentacaoDao {

	@Inject //@PersistenceContext
	private EntityManager manager;

	public void adiciona(Movimentacao movimentacao) {
		this.manager.joinTransaction();
		this.manager.persist(movimentacao);
		
		if(movimentacao.getValor().compareTo(BigDecimal.ZERO) < 0) {
			throw new ValorInvalidoException("Movimentac20/09/2014ao negativa");
		}
	}

	public Movimentacao busca(Integer id) {
		return this.manager.find(Movimentacao.class, id);
	}

	public void remove(Movimentacao movimentacao) {
		Movimentacao movimentacaoParaRemover = this.manager.find(Movimentacao.class, movimentacao.getId());
		this.manager.remove(movimentacaoParaRemover);
	}
	
	public List<Movimentacao> lista() {
		return this.manager.createQuery("select m from Movimentacao m", Movimentacao.class).getResultList();
	}

	public List<Movimentacao> listaTodasMovimentacoes(Conta conta) {
		String jpql = "select m from Movimentacao m "
				+ "where m.conta = :conta order by m.valor desc ";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("conta", conta);
		
		return query.getResultList();
	}
	
	public List<Movimentacao> listaPorValorETipo(BigDecimal valor, TipoMovimentacao tipoMovimentacao) {
		String jpql = "select m from Movimentacao m "
				+ "where m.valor = :valor and m.tipoMovimentacao = :tipoMovimentacao " +
				"order by m.valor desc ";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("valor", valor);
		query.setParameter("tipoMovimentacao", tipoMovimentacao);
		
		
		return query.getResultList();
	}
	
	public BigDecimal calculaTotalMovimentado(Conta conta, TipoMovimentacao tipo) {
		
		String jpql = "select sum(m.valor) from Movimentacao m " +
				"where m.conta = :conta and m.tipoMovimentacao = :tipo";
		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class)
				.setParameter("conta", conta)
				.setParameter("tipo", tipo);
		
		return query.getSingleResult();
	}

	public List<Movimentacao> buscaTodasMovimentacoes(String titular) {
		String jpql = "select m from Movimentacao m " +
				"where m.conta.titular like :titular";
		TypedQuery<Movimentacao> query = this.manager.createQuery(jpql, Movimentacao.class)
				.setParameter("titular", "%" + titular + "%");
		
		return query.getResultList();
	}


	public List<Movimentacao> buscaTodasMovimentacoesDaConta(String titular) {
		TypedQuery<Movimentacao> query = this.manager
				.createNamedQuery("Movimentacao.buscaTodasMovimentacoesDaConta", Movimentacao.class)
				.setParameter("titular", "%" + titular + "%");
		
		return query.getResultList();
	}
	
	public List<ValorPorMesEAno> listaMesesComMovimentacoes(Conta conta, TipoMovimentacao tipo) {
		String jpql = "select new br.com.caelum.financas.modelo.ValorPorMesEAno(month(m.data), " +
				"year(m.data), sum(m.valor))" +
				"from Movimentacao m " +
				"where m.conta = :conta and m.tipoMovimentacao = :tipo " +
				"group by year(m.data)||month(m.data) " +
				"order by sum(m.valor) desc";
		TypedQuery<ValorPorMesEAno> query = this.manager.createQuery(jpql, ValorPorMesEAno.class)
				.setParameter("conta", conta)
				.setParameter("tipo", tipo);
		
		return query.getResultList();
	}
	
	public List<Movimentacao> listaComCategorias() {
		return this.manager.createQuery("select distinct m from Movimentacao m left join fetch m.categorias", Movimentacao.class).getResultList();
	}
	
	public int quantidadeDeMovimentacoesPorConta(Conta conta) {
		return listaTodasMovimentacoes(conta).size();
	}
}