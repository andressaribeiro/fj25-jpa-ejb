package br.com.caelum.financas.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Andressa Albuquerque
 * 
 */
@Entity
public class Movimentacao {

	@Id
	@GeneratedValue
	private Integer id;

	private String descricao;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;

	private BigDecimal valor;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Conta conta;

	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	@PreUpdate
	@PrePersist
	public void preAltera() {
		System.out.println("Atualizando a data de movimentacao");
		this.setData(Calendar.getInstance());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(" Id: " + this.id + "\n");
		sb.append(" Descricao: " + this.descricao + "\n");
		sb.append(" Valor: " + this.valor + "\n");
		sb.append(" Tipo de Movimentacao: " + this.tipoMovimentacao + "\n");
		sb.append(" Numero da Conta: " + this.conta.getNumero() + "\n");

		return sb.toString();
	}

}
