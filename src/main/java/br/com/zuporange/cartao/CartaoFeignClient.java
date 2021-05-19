package br.com.zuporange.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="cartao", url="http://localhost:8888")
public interface CartaoFeignClient {

	@GetMapping("/api/cartoes")
	CartaoResponse cadastroCartao(@RequestParam("idProposta") String idProposta);
	
}
