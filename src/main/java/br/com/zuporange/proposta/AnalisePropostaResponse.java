package br.com.zuporange.proposta;

public class AnalisePropostaResponse {
	
	private String documento;
	private String nome;
	private String id;
	private ResultadoSolicitacao resultadoSolicitacao;

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getId() {
		return id;
	}

	public ResultadoSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public Status status() {
	
		return resultadoSolicitacao.getStatus();
	}
	
	
	
}
