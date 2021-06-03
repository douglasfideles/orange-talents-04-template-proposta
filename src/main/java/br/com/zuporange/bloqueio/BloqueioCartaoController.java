package br.com.zuporange.bloqueio;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zuporange.cartao.Cartao;
import br.com.zuporange.cartao.CartaoFeignClient;
import br.com.zuporange.cartao.CartaoRepository;
import br.com.zuporange.cartao.StatusCartao;
import feign.FeignException;

@RestController
@RequestMapping("/bloqueio")
public class BloqueioCartaoController {
	
	private CartaoRepository cartaoRepository;
	
	private CartaoFeignClient cartaoFeignClient;
	
	private BloqueioCartaoRepository bloqueioCartaoRepository;
	
	
	public BloqueioCartaoController(CartaoRepository cartaoRepository, CartaoFeignClient cartaoFeignClient, BloqueioCartaoRepository bloqueioCartaoRepository) {
		
		this.cartaoRepository = cartaoRepository;
		this.cartaoFeignClient = cartaoFeignClient;
		this.bloqueioCartaoRepository = bloqueioCartaoRepository;
		
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> bloquearCartao(@RequestParam("id") String id, 
			HttpServletRequest request, @RequestBody @Valid BloqueioRequest bloqueioRequest, UriComponentsBuilder uriComponentsBuilder){
		
		Optional<Cartao> checaCartao = cartaoRepository.findById(id);
		
		if(checaCartao.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		if(checaCartao.get().getStatusCartao() == StatusCartao.BLOQUEADO) {
			
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Cartao ja bloqueado");
			
		}
		
		return bloquearCartao(checaCartao.get(), bloqueioRequest, request, uriComponentsBuilder);
		
	}

	private ResponseEntity<?> bloquearCartao(Cartao cartao, @RequestBody @Valid BloqueioRequest bloqueioRequest,
			HttpServletRequest request, UriComponentsBuilder uriComponentsBuilder) {
		
		try {
			
			BloqueioCartao bloqueioCartao = new BloqueioCartao(request.getLocalAddr(), request.getHeader("user-agent"), cartao);
			bloqueioCartao.bloquearCartao(cartao);
			
			bloqueioCartao = bloqueioCartaoRepository.save(bloqueioCartao);
			
			cartaoFeignClient.bloqueioCartao(cartao.getNumeroCartao(), new BloqueioRequest(bloqueioRequest));
			
			URI uri = uriComponentsBuilder.path("/bloqueio/{id}").build(bloqueioCartao.getId());
            return ResponseEntity.created(uri).build();
			
		} catch (FeignException.UnprocessableEntity e) {
			
			 throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Falha ao bloquear o cartão.");
			
		}
		
	}
	
}
