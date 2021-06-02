package br.com.zuporange.bloqueio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zuporange.cartao.Cartao;

public class BloqueioRequest {

	@NotBlank
	private String sistemaResponsavel;
	
	@Deprecated
    public BloqueioRequest() {
    }
	
	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
	
	public BloqueioRequest(BloqueioRequest request) {
        this.sistemaResponsavel = request.getSistemaResponsavel();
    }

	public BloqueioCartao toModel(String ipSolicitante, String userAgent,
			Cartao cartao) {
		
        return new BloqueioCartao(ipSolicitante, userAgent, cartao);
        
        
        
    }
	
}
