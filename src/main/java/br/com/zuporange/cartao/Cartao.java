package br.com.zuporange.cartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.zuporange.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime emitidoEm = LocalDateTime.now();
	
	private String titular;
	
	private Integer limite;
	
	@OneToOne @JoinColumn(name="proposta")
    private Proposta proposta;
    
    
    @Deprecated
	public Cartao() {
	}
    
	public Cartao(Long id, LocalDateTime emitidoEm, String titular, Integer limite) {
		
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;

	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public Integer getLimite() {
		return limite;
	}

	public Proposta getProposta() {
		return proposta;
	}
    
    

}
