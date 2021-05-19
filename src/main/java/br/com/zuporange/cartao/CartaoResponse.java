package br.com.zuporange.cartao;

import java.time.LocalDateTime;

import br.com.zuporange.proposta.Proposta;

public class CartaoResponse {

	private Long id;
    private String numero;
    private String titular;
    private LocalDateTime emitidoEm;
    private Integer limite;
    
	public Long getId() {
		return id;
	}
	
	public String getNumero() {
		return numero;
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
	
    public Cartao toModel() {
    	
    	return new Cartao(id, emitidoEm, titular, limite);
    	
    }
    
}
