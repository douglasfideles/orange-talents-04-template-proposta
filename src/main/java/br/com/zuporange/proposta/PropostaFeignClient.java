package br.com.zuporange.proposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="consulta", url="http://localhost:9999")
public interface PropostaFeignClient {

	@PostMapping("/api/solicitacao")
	void solicitacao();
	
}
