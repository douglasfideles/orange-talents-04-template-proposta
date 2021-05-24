package br.com.zuporange.biometria;

import java.util.Base64;

import javax.validation.constraints.NotBlank;

import br.com.zuporange.cartao.Cartao;

public class BiometriaRequest {

	@NotBlank
	private String fingerprint;
	
	
	@Deprecated
	public BiometriaRequest() {
	}


	public String getFingerprint() {
		return fingerprint;
	}


	public Biometria toBiometria(Cartao cartao, byte[] fingerprint) {
		
		String base64Biometria = Base64.getEncoder().encodeToString(fingerprint);
		return new Biometria(cartao, base64Biometria);
	}
	
	
	
}
