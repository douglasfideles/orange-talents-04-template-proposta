package br.com.zuporange.viagem;

import java.time.LocalDate;

public class ViagemFeignRequest {

	private String destino;
    private LocalDate validoAte;
    
    
    
	public ViagemFeignRequest(Viagem viagem) {
		
		this.destino = viagem.getDestino();
		this.validoAte = viagem.getDataTermino();
		
	}
	
	public String getDestino() {
		return destino;
	}
	public LocalDate getValidoAte() {
		return validoAte;
	}

    
    
	
}
