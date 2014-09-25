package br.com.caelum.financas.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author Andressa Albuquerque
 *
 */
@SuppressWarnings("deprecation")
// isso indica para o hibernate das update na tabelas apenas os campos que realmente mudaram ao inves de todos os campos
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Entity
public class Conta {

	@Id
	@GeneratedValue
	private Integer id;

	private String titular;

	private String agencia;

	private String numero;

	private String banco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(" Id: " + this.id + "\n");
		sb.append(" Titular: " + this.titular + "\n");
		sb.append(" Agencia: " + this.agencia + "\n");
		sb.append(" Numero: " + this.numero + "\n");
		sb.append(" Banco: " + this.banco + "\n");

		return sb.toString();
	}

}
