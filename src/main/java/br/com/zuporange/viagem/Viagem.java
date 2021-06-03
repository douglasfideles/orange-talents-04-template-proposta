package br.com.zuporange.viagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zuporange.cartao.Cartao;

@Entity
public class Viagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String destino;
	
	@NotNull
	private LocalDate dataTermino;
	private LocalDateTime instanteViagem = LocalDateTime.now();
	private String ipClient;
	private String userAgent;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;
	
	@Deprecated
	public Viagem() {
	}
	
	
	
	public Viagem(String destino, LocalDate dataTermino, String ipClient,
			String userAgent) {

		this.destino = destino;
		this.dataTermino = dataTermino;
		this.ipClient = ipClient;
		this.userAgent = userAgent;
		
	}

	public Long getId() {
		return id;
	}
	
	public String getDestino() {
		return destino;
	}
	public LocalDate getDataTermino() {
		return dataTermino;
	}
	public LocalDateTime getInstanteViagem() {
		return instanteViagem;
	}
	public String getIpClient() {
		return ipClient;
	}
	public String getUserAgent() {
		return userAgent;
	}
	
	
	
}
