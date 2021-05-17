package br.com.zuporange.proposta;

import java.math.BigDecimal;

public class PropostaResponse {
	
private String id;
	
	private String documento;

	private String email;

	private String nome;
	
	private String endereco;

	private BigDecimal salario;
	
	
	public PropostaResponse(Proposta proposta) {
		
		id = proposta.getId();
		documento = proposta.getDocumento();
		email = proposta.getEmail();
		nome = proposta.getNome();
		endereco = proposta.getEndereco();
		salario = proposta.getSalario();
		
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
