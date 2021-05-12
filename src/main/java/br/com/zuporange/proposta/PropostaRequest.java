package br.com.zuporange.proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class PropostaRequest {
	
	@NotBlank
	@CpfOuCnpj
	private String documento;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Length(min = 3)
	private String nome;
	
	@NotBlank
	private String endereco;
	
	@NotNull
	@Positive
	private BigDecimal salario;
	
	public PropostaRequest(String documento, String email,
			String nome, String endereco, BigDecimal salario) {

		this.documento = documento.replaceAll("[\\.\\-\\/]", "");
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
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
	public Proposta toModel() {
		
		return new Proposta(documento, email, nome, endereco, salario);
		
	}
	
}
