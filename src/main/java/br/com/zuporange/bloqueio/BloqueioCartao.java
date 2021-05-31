package br.com.zuporange.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zuporange.cartao.Cartao;

@Entity
public class BloqueioCartao {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime horaBloqueio = LocalDateTime.now();
	@NotBlank
	private String ipSolicitante;
	@NotBlank
	private String userAgent;
	@NotNull @OneToOne @JoinColumn(name = "cartao_id")
	private Cartao cartao;
	
	@Deprecated
	public BloqueioCartao() {
	
	}
	
	public BloqueioCartao(LocalDateTime horaBloqueio, String ipSolicitante, String userAgent,
			Cartao cartao) {

		this.horaBloqueio = horaBloqueio;
		this.ipSolicitante = ipSolicitante;
		this.userAgent = userAgent;
		this.cartao = cartao;
		
	}



	public Long getId() {
		return id;
	}

	public LocalDateTime getHoraBloqueio() {
		return horaBloqueio;
	}

	public String getIpSolicitante() {
		return ipSolicitante;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
	
	
	
}
