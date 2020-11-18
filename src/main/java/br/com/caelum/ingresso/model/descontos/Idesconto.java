package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public interface Idesconto {
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal);
}
