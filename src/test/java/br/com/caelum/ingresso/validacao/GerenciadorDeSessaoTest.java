package br.com.caelum.ingresso.validacao;

import java.time.Duration;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {

	private Filme rogueOne; 
	private Sala sala3D; 
	private Sessao sessaoDasDez; 
	private Sessao sessaoDasTreze; 
	private Sessao sessaoDasDezoito; 
	
	
	public void preparaSessoes() {
		this.rogueOne = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI");
		this.sala3D = new Sala("Sala 3D");
	}
	
}
