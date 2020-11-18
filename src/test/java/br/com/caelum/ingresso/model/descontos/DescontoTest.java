package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import junit.framework.Assert;

public class DescontoTest {
	
	Sala sala; 
	Filme filme; 
	Sessao sessao; 
	
	@Before
	public void preparaSessaoDesconto() {
		sala = new Sala("Eldorado Max", new BigDecimal("20.5"));
		filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
	}

	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		Ingresso ingresso = new Ingresso(sessao, new SemDesconto());
		Assert.assertEquals(ingresso.getPreco(), new BigDecimal("32.50"));		
	}
	
	@Test
	public void deveConcederDescontoParaIngressoDeEstudante() {
		Ingresso ingresso = new Ingresso(sessao, new DescontoParaEstudantes());
		BigDecimal valorComDesconto = sessao.getPreco().divide(new BigDecimal("2.0"));
		Assert.assertEquals(ingresso.getPreco(), valorComDesconto);
		
	}
	
	@Test 
	public void deveConcederDescontoParaIngressoDeFuncionariosDeBanco() {
		Ingresso ingresso = new Ingresso(sessao, new DescontoParaBancos());
		BigDecimal valorComDesconto = sessao.getPreco().subtract(sessao.getPreco().multiply(new BigDecimal("0.3")));
		Assert.assertEquals(ingresso.getPreco(), valorComDesconto.setScale(2, RoundingMode.HALF_UP));
	}
}
