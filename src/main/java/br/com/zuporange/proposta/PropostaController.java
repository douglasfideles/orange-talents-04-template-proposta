package br.com.zuporange.proposta;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	private PropostaRepository propostaRepository;
	

	public PropostaController(PropostaRepository propostaRepository) {
		super();
		this.propostaRepository = propostaRepository;
	}

	@PostMapping
	public ResponseEntity<PropostaResponse> cadastrar(@RequestBody @Valid PropostaRequest propostaRequest, 
			UriComponentsBuilder uriComponentsBuilder) {
		
		Proposta proposta = propostaRequest.toModel();
		Optional<Proposta> checarDocumento = propostaRepository.findByDocumento(propostaRequest.getDocumento());
		if(checarDocumento.isPresent()) {
			
			return ResponseEntity.status(422).build();
			
		}
		
		propostaRepository.save(proposta);
		URI uriProposta = uriComponentsBuilder.path("/propostas/{id}").build(proposta.getId());
		return ResponseEntity.created(uriProposta).body(new PropostaResponse(proposta));
		
	}
	
}
