package br.com.zuporange.cartao;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import br.com.zuporange.bloqueio.BloqueioCartao;
import br.com.zuporange.proposta.Proposta;
import br.com.zuporange.proposta.Status;

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
	
	@OneToOne(cascade = CascadeType.MERGE) @JoinColumn(name = "bloqueio_cartao")
    private BloqueioCartao bloqueio;
	
	@Enumerated(value = EnumType.STRING)
	private StatusCartao statusCartao = StatusCartao.ATIVO;
	
    @Deprecated
	public Cartao() {
	}
    
	public Cartao(String numeroCartao, LocalDateTime emitidoEm, String titular, Integer limite, Proposta proposta) {
		

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

	public BloqueioCartao getBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(BloqueioCartao bloqueio) {
		this.bloqueio = bloqueio;
	}

	public StatusCartao getStatusCartao() {
		return statusCartao;
	}
	
	public boolean bloqueado() {
		
		return this.statusCartao.equals(StatusCartao.BLOQUEADO);
		
	}
	
	public void adicionaBloqueio() {
        this.statusCartao = StatusCartao.BLOQUEADO;
    }
	
}
