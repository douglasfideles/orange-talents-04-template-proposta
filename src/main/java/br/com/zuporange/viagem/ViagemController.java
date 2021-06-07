package br.com.zuporange.viagem;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zuporange.cartao.Cartao;
import br.com.zuporange.cartao.CartaoFeignClient;
import br.com.zuporange.cartao.CartaoRepository;
import feign.FeignException;

@RestController
@RequestMapping("/viagem")
public class ViagemController {
	
	private CartaoRepository cartaoRepository;
	private ViagemRepository viagemRepository;
	private CartaoFeignClient cartaoFeignClient;
	
	
	
	public ViagemController(CartaoRepository cartaoRepository, ViagemRepository viagemRepository,
			CartaoFeignClient cartaoFeignClient) {
		
		this.cartaoRepository = cartaoRepository;
		this.viagemRepository = viagemRepository;
		this.cartaoFeignClient = cartaoFeignClient;
		
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> cadastrarAviso(@RequestBody @Valid ViagemRequest request, @PathVariable("id") String idCartao, HttpServletRequest servletRequest,
            @RequestHeader(value = "User-Agent") String userAgent){
		
		Optional<Cartao> checaCartao = cartaoRepository.findById(idCartao);
		
		if(checaCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			
			ViagemResponse avisoResponse = cartaoFeignClient.avisoViagem(idCartao, request);
			
			Viagem viagem = request.toModel(checaCartao.get(), idCartao, userAgent);
			viagemRepository.save(viagem);
			
			return ResponseEntity.ok(avisoResponse);
			
		} catch (FeignException.UnprocessableEntity e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nao armazenado!");
			
		}
	}
	
}
