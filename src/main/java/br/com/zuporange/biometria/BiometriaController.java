package br.com.zuporange.biometria;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zuporange.cartao.Cartao;
import br.com.zuporange.cartao.CartaoRepository;

@RestController
@RequestMapping("/cartaoes")
public class BiometriaController {

	private CartaoRepository cartaoRepository;
	private BiometriaRepository biometriaRepository;
	
	@Autowired
	public BiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
		this.cartaoRepository = cartaoRepository;
		this.biometriaRepository = biometriaRepository;
	}
	
	@PostMapping("/biometria/{id}")
	public ResponseEntity<?> cadastroBiometria(@PathVariable("id") String id, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder builder){
		
		Optional<Cartao> existeCartao = cartaoRepository.findById(id);
		
		if(existeCartao.isPresent()) {
			
			Biometria biometria = request.toBiometria(existeCartao.get(), request.getFingerprint().getBytes());
			biometriaRepository.save(biometria);
			URI uri = builder.path("/propostas/{id}").buildAndExpand(biometria.getId()).toUri();
			return ResponseEntity.created(uri).build();
			
			
		}
		
		if(existeCartao.isEmpty()) {
			
			return ResponseEntity.notFound().build();
			
		}
		
		return ResponseEntity.badRequest().build();
	
		
	}
	
}
