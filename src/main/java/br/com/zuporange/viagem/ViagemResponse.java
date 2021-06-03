package br.com.zuporange.viagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ViagemResponse {

	private String destino;
	private LocalDateTime dataTermino;
	private LocalDate instanteViagem;
	
	
	
	public ViagemResponse(String destino, LocalDateTime dataTermino, LocalDate instanteViagem) {

		this.destino = destino;
		this.dataTermino = dataTermino;
		this.instanteViagem = instanteViagem;
		
	}
	
	public String getDestino() {
		return destino;
	}
	public LocalDateTime getDataTermino() {
		return dataTermino;
	}
	public LocalDate getInstanteViagem() {
		return instanteViagem;
	}
	
	
	
}
