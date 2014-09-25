package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

/**
 * 
 * @author Andressa Albuquerque
 *
 */
public class TestaInsereMovimentacao {

	public static void main(String[] args) {
		EntityManager manager = JPAUtil.getEntityManager();

		MovimentacaoDao dao = new MovimentacaoDao(manager);

		Conta conta = new Conta();
		conta.setTitular("Nicolaus");
		conta.setBanco("Banco Santander");
		conta.setNumero("123456-6");
		conta.setAgencia("12345");

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("Nova movimentacao2");
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao.setValor(new BigDecimal(123));

		dao.adiciona(movimentacao);

		manager.getTransaction().begin();
		manager.getTransaction().commit();
		manager.close();
		
		System.out.println("Movimentacao gravada com sucesso!");
	}

}
