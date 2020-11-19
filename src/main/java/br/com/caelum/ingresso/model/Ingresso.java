package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.caelum.ingresso.model.descontos.TipoDeIngresso;
import br.com.caelum.ingresso.model.Sessao; 

public class Ingresso {
	private Sessao sessao; 
	private BigDecimal preco; 
	private Lugar lugar; 
	
	public Ingresso(Sessao sessao, TipoDeIngresso tipoDeIngresso, Lugar lugar) {
		this.sessao = sessao; 
		this.preco = tipoDeIngresso.aplicaDesconto(sessao.getPreco());
		this.lugar = lugar; 
	}
	
	public BigDecimal getPreco() {
		return preco.setScale(2, RoundingMode.HALF_UP);
	}
	
	public void setPreco(BigDecimal preco) {
		this.preco = preco; 
	}
}
