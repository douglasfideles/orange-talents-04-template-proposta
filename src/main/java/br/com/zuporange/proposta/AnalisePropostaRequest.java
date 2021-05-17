package br.com.zuporange.proposta;

public class AnalisePropostaRequest {
	
	private String documento;
	private String nome;
	private String id;
	
	@Deprecated
	public AnalisePropostaRequest() {}
	
	public AnalisePropostaRequest(String documento, String nome, String id) {

		this.documento = documento;
		this.nome = nome;
		this.id = id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getId() {
		return id;
	}
	
	
	
	
}
