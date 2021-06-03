package br.com.zuporange.viagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zuporange.cartao.Cartao;

public class ViagemRequest {

	@NotBlank
	private String destino;
	
	@NotNull
	private LocalDate dataTermino;

	public String getDestino() {
		return destino;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}
	
	public Viagem toModel(Cartao cartao, String ipClient, String userAgent) {
		
		return new Viagem(this.destino, this.dataTermino, ipClient, userAgent);
		
	}
	
}
