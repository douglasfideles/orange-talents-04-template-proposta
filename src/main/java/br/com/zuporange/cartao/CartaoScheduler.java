package br.com.zuporange.cartao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
	
	@Scheduled(fixedDelay = 1000)
	public void associaCartaoProposta() {
		
		List<Proposta> propostas = propostaRepository.findByStatus(Status.ELEGIVEL);
	
		for (Proposta proposta : propostas) {
			
			try {
				
				CartaoResponse cartaoResponse = cartaoFeignClient.cadastroCartao(proposta.getId());
				
				Cartao cartao = cartaoResponse.toModel();
				
				System.out.println(cartao.getId());
				
				cartaoRepository.save(cartao);
				
				
			} catch (Exception e) {
				
				System.out.println("Erro ao associar proposta ao cartão: " + proposta.getId());
				
			}
			
		}
		
	}
	
}
