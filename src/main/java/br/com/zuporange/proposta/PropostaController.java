package br.com.zuporange.proposta;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import feign.FeignException;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	private PropostaRepository propostaRepository;
	private PropostaFeignClient propostaFeignClient;
	
	public PropostaController(PropostaRepository propostaRepository, PropostaFeignClient propostaFeignClient) {

		this.propostaRepository = propostaRepository;
		this.propostaFeignClient = propostaFeignClient;
		
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
		
		try {
			
			AnalisePropostaRequest analiseRequest = new AnalisePropostaRequest(proposta.getDocumento(), proposta.getNome(), proposta.getId());
			
			
			AnalisePropostaResponse resultadoConsulta =  propostaFeignClient.solicitacao(analiseRequest);
			
			System.out.println(resultadoConsulta.getIdProposta());
			
			Status status = resultadoConsulta.status();
			proposta.setStatus(status);
			
		} catch (FeignException.UnprocessableEntity unprocessableTntity) {
			
			proposta.setStatus(Status.NAO_ELEGIVEL);
			
		} catch (FeignException.ServiceUnavailable serviceUnvailable) {
			propostaRepository.delete(proposta);
		}
		
		
		propostaRepository.save(proposta);		
		URI uriProposta = uriComponentsBuilder.path("/propostas/{id}").build(proposta.getId());
		
		return ResponseEntity.created(uriProposta).body(new PropostaResponse(proposta));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PropostaDetalheResponse> checarProposta(@PathVariable String id){
		
		Optional<Proposta> proposta = propostaRepository.findById(id);
		
		if(proposta.isPresent()) {
			
			return ResponseEntity.ok(new PropostaDetalheResponse(proposta.get()));
			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
}
