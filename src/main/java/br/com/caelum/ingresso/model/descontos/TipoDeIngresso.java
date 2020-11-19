package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public enum TipoDeIngresso {
	
	INTEIRO(new SemDesconto()), 
	ESTUDANTE(new DescontoParaEstudantes()), 
	BANCO(new DescontoParaBancos());
	
	private final Idesconto desconto; 
	
	TipoDeIngresso(Idesconto desconto){
		this.desconto = desconto; 
	}
	
	public BigDecimal aplicaDesconto(BigDecimal valor) {
		return desconto.aplicarDescontoSobre(valor);
	}
	
	public String getDescricao() {
		return desconto.getDescricao();
	}
}
