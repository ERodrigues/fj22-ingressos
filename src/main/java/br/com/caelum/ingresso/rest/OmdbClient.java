package br.com.caelum.ingresso.rest;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.DetalheDoFilme;
import br.com.caelum.ingresso.model.Filme;

@Component
public class OmdbClient {
	
	public Optional<DetalheDoFilme> request(Filme filme){
		RestTemplate client = new RestTemplate(); 
		String titulo = filme.getNome().replace(" ", "+"); 
		String url = String.format("https://omdb-fj22.herokuapp.com/movie?title=%s", titulo);
		
		try {
			DetalheDoFilme detalhe = client.getForObject(url, DetalheDoFilme.class);
			return Optional.ofNullable(detalhe);
		} catch (RestClientException e) {
			// TODO: handle exception
			return Optional.empty(); 
		}
	}
}
