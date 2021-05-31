package br.com.zuporange.cartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import br.com.zuporange.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	private String numeroCartao;
	
	private LocalDateTime emitidoEm = LocalDateTime.now();
	
	private String titular;
	
	private Integer limite;

	@OneToOne(mappedBy = "cartao")
	private Proposta proposta;
	
    @Deprecated
	public Cartao() {
	}
    
	public Cartao(String id, String numeroCartao, LocalDateTime emitidoEm, String titular, Integer limite, Proposta proposta) {
		
		this.id = id;
		this.numeroCartao = numeroCartao;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.proposta = proposta;
		
	}

	public String getId() {
		return id;
	}
	
	public String getNumeroCartao() {
		return numeroCartao;
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
