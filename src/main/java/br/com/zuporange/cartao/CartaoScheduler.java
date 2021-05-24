package br.com.zuporange.cartao;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.LoggerFactory;

import br.com.zuporange.proposta.Proposta;
import br.com.zuporange.proposta.PropostaRepository;
import br.com.zuporange.proposta.Status;
import feign.FeignException;

@Component
public class CartaoScheduler {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private CartaoFeignClient cartaoFeignClient;
	
	
	@Deprecated
	public CartaoScheduler() {
	}
	
	@Scheduled(fixedDelay = 1000 * 60)
	public void associaCartaoProposta() {
		
		List<Proposta> propostas = propostaRepository.findByStatus(Status.ELEGIVEL);
	
		for (Proposta proposta : propostas) {
			
			try {
				
				CartaoResponse cartaoResponse = cartaoFeignClient.cadastroCartao(proposta.getId());
				
				proposta.setCartao(cartaoResponse.toModel()); 
				
				propostaRepository.save(proposta);
				
				System.out.println("Proposta de documento " + proposta.getDocumento() + " e cartao "+ cartaoResponse.getId() +" criados com sucesso!");
				
				
			} catch (FeignException e) {
				
				 e.printStackTrace();
				
			}
			
		}
		
	}
	
}
