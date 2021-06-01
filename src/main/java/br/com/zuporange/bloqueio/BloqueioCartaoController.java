package br.com.zuporange.bloqueio;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zuporange.cartao.Cartao;
import br.com.zuporange.cartao.CartaoRepository;

@RestController
@RequestMapping("/bloqueio")
public class BloqueioCartaoController {
	
	private CartaoRepository cartaoRepository;
	
	
	
	public BloqueioCartaoController(CartaoRepository cartaoRepository) {
		this.cartaoRepository = cartaoRepository;
	}



	@PostMapping("/{id}")
	public ResponseEntity<?> bloquearCartao(@RequestParam("id") String id, @RequestHeader(value = "user-agent") String userAgent, HttpServletRequest request, UriComponentsBuilder uriComponentsBuilder){
		
		Optional<Cartao> checaCartao = cartaoRepository.findById(id);
		
		return checaCartao.map(cartaoEncontrado -> {
			
			if(cartaoEncontrado.bloqueado()) {
				
				return ResponseEntity.unprocessableEntity().body("Cartão bloqueado");
				
			}
			
			BloqueioCartao bloqueioCartao = new BloqueioCartao(request.getRemoteAddr(), userAgent, cartaoEncontrado);
			executaBloqueio(bloqueioCartao, cartaoEncontrado);
			
			return ResponseEntity.ok().body("Cartão bloqueado");
		}).orElseGet(() -> ResponseEntity.notFound().build());
		
	}



	private void executaBloqueio(BloqueioCartao bloqueioCartao, Cartao cartaoEncontrado) {
		
		
	}
	
}
