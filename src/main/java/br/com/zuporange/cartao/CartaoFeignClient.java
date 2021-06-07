package br.com.zuporange.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zuporange.bloqueio.BloqueioCartaoResponse;
import br.com.zuporange.bloqueio.BloqueioRequest;
import br.com.zuporange.viagem.ViagemFeignRequest;
import br.com.zuporange.viagem.ViagemRequest;
import br.com.zuporange.viagem.ViagemResponse;

@FeignClient(name="cartao", url="http://localhost:8888")
public interface CartaoFeignClient {

	@GetMapping("/api/cartoes")
	CartaoResponse cadastroCartao(@RequestParam(name="idProposta") String idProposta);
	
	@PostMapping("/{id}/bloqueios")
    public BloqueioCartaoResponse bloqueioCartao(@PathVariable(name = "id") String numeroCartao, @RequestBody BloqueioRequest request);
	
	@PostMapping("/api/cartoes/{id}/avisos")
	public ViagemResponse avisoViagem(@PathVariable(name= "id") String numeroCartao, @RequestBody ViagemRequest request);
	
}
