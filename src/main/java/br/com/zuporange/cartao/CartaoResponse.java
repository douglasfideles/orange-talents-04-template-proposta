package br.com.zuporange.cartao;

import java.time.LocalDateTime;

import br.com.zuporange.proposta.Proposta;
import br.com.zuporange.proposta.PropostaRepository;

public class CartaoResponse {

	private String id;
    private String numeroCartao;
    private String titular;
    private LocalDateTime emitidoEm;
    private Integer limite;
    private String idProposta;
    
    
	public String getId() {
		return id;
	}
	
	public String getNumeroCartao() {
		return numeroCartao;
	}
	
	public String getTitular() {
		return titular;
	}
	
	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}
	
	public Integer getLimite() {
		return limite;
	}
	
	public String getIdProposta() {
		return idProposta;
	}

	public Cartao toModel(PropostaRepository propostaRepository) {
    	
		Proposta proposta = propostaRepository.findById(idProposta).get();
    	return new Cartao(numeroCartao, emitidoEm, titular, limite, proposta);
    	
    }
    
}
