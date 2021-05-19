package br.com.zuporange.proposta;

import java.math.BigDecimal;

public class PropostaDetalheResponse {

	
	private String documento;

	private String email;

	private String nome;
	
	private String endereco;

	private BigDecimal salario;
	
	public PropostaDetalheResponse(Proposta proposta) {
		

		documento = proposta.getDocumento();
		email = proposta.getEmail();
		nome = proposta.getNome();
		endereco = proposta.getEndereco();
		salario = proposta.getSalario();
	
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
