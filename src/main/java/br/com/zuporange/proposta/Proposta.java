package br.com.zuporange.proposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

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
	
	
	
	
}
