package br.com.zuporange.cartao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CartaoRepository extends CrudRepository<Cartao, String>{

	Optional<Cartao> findByStatusCartao(StatusCartao statusCartao);
	
}
