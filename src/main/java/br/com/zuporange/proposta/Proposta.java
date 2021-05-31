package br.com.zuporange.proposta;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import br.com.zuporange.cartao.Cartao;

@Entity
public class Proposta {
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(nullable = false, unique = true)
	private String documento;
	
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String endereco;
	
	@Column(nullable = false)
	private BigDecimal salario;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToOne
	private Cartao cartao;
	
	@Deprecated
	public Proposta() {

	}

	/*
	public Proposta(PropostaRequest propostaRequest) {
		
		documento = propostaRequest.getDocumento();
		email = propostaRequest.getEmail();
		nome = propostaRequest.getNome();
		endereco = propostaRequest.getEndereco();
		salario = propostaRequest.getSalario();
		
	}*/

	
	public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario, Cartao cartao) {

		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.cartao = cartao;
		
	}
	
	public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {

		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		
		
	}
	
	
	public String getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}


	public String getEmail() {
		return email;
	}


	public String getNome() {
		return nome;
	}


	public String getEndereco() {
		return endereco;
	}


	public BigDecimal getSalario() {
		return salario;
	}

	public void setStatus(Status status) {
		
		this.status = status;
		
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	
	
	
}
