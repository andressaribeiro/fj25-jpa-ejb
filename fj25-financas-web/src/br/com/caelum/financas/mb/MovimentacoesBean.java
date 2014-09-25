package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.com.caelum.financas.dao.CategoriaDao;
import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

@ManagedBean
@SessionScoped
public class MovimentacoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MovimentacaoDao movimentacaoDao;
	
	@Inject
	private ContaDao contaDao;
	
	@Inject
	private CategoriaDao categoriaDao;

	private List<Categoria> categorias;
	private List<Movimentacao> movimentacoes;
	private Movimentacao movimentacao = new Movimentacao();
	private Integer contaId;
	private Integer categoriaId;

	public void grava() {
		System.out.println("Fazendo a gravacao da movimentacao");
		
		Conta contaRelacionada = contaDao.busca(this.contaId);
		
		movimentacao.setConta(contaRelacionada);
		movimentacaoDao.adiciona(this.movimentacao);
		movimentacoes = movimentacaoDao.listaComCategorias();

		limpaFormularioDoJSF();
	}

	public void remove() {
		System.out.println("Removendo a movimentacao");

		movimentacaoDao.remove(this.movimentacao);
		movimentacoes = movimentacaoDao.listaComCategorias();

		limpaFormularioDoJSF();
	}
	
	public void adicionaCategoria() {
		if (this.categoriaId != null && this.categoriaId > 0) {
			Categoria categoria = categoriaDao.procura(this.categoriaId);
			this.movimentacao.getCategorias().add(categoria);
		}
	}

	public List<Movimentacao> getMovimentacoes() {

		if (movimentacoes == null) {
//			movimentacoes = movimentacaoDao.listaComCategorias();
			movimentacoes = movimentacaoDao.lista();
		}

		return movimentacoes;
	}

	public Movimentacao getMovimentacao() {
		if (movimentacao.getData() == null) {
			movimentacao.setData(Calendar.getInstance());
		}
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento manager que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.movimentacao = new Movimentacao();
	}

	public TipoMovimentacao[] getTiposDeMovimentacao() {
		return TipoMovimentacao.values();
	}

	public List<Categoria> getCategorias() {
		
		if(this.categorias == null) {
			System.out.println("Listando categorias");
			this.categorias = this.categoriaDao.lista();
		}
		
		return this.categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}