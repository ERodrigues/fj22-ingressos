package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {
	private List<Sessao> sessoes; 
	
	public GerenciadorDeSessao(List<Sessao> sessoesDaSala) {
		this.sessoes = sessoesDaSala; 
	}
	
	public boolean cabe(Sessao sessaoNova) {
		if (terminaAmanha(sessaoNova)) {
			return false; 
		}
		
		return sessoes.stream().noneMatch(sessaoExistente -> 
										  horarioIsConflitante(sessaoExistente, sessaoNova)
						);
	}

	private boolean terminaAmanha(Sessao sessao) {
		LocalDateTime terminoSessaoNova = getTerminoSessaoComDiaDeHoje(sessao);
		LocalDateTime ultimoSegundoDeHoje = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		
		if (terminoSessaoNova.isAfter(ultimoSegundoDeHoje)) {
			return true; 
		}
		
		return false; 
	}

	private LocalDateTime getTerminoSessaoComDiaDeHoje(Sessao sessao) {
		// TODO Auto-generated method stub
		LocalDateTime inicioSessaoNova = getInicioSessaoComDiaDeHoje(sessao);
		return inicioSessaoNova.plus(sessao.getFilme().getDuracao());
	}

	private boolean horarioIsConflitante(Sessao sessaoExistente, Sessao sessaoNova) {
		// TODO Auto-generated method stub
		LocalDateTime inicioSessaoExistente = getInicioSessaoComDiaDeHoje(sessaoExistente);
		LocalDateTime terminoSessaoExistente = getTerminoSessaoComDiaDeHoje(sessaoExistente);
		LocalDateTime inicioSessaoNova = getInicioSessaoComDiaDeHoje(sessaoNova);
		LocalDateTime terminoSessaoNova = getTerminoSessaoComDiaDeHoje(sessaoNova);
		
		boolean sessaoNovaTerminaAntesDaExistente = terminoSessaoNova.isBefore(inicioSessaoExistente); 
		boolean sessaoNovaComecaDepoisDaExistente = terminoSessaoExistente.isBefore(inicioSessaoNova);
		
		if (sessaoNovaTerminaAntesDaExistente || sessaoNovaComecaDepoisDaExistente) {
			return false; 
		}
		return true;
	}

	private LocalDateTime getInicioSessaoComDiaDeHoje(Sessao sessao) {
		// TODO Auto-generated method stub
		LocalDate hoje = LocalDate.now();
		return sessao.getHorario().atDate(hoje);
	}
}
