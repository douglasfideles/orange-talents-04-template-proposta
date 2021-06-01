package br.com.zuporange.cartao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.zuporange.proposta.Proposta;

public interface CartaoRepository extends CrudRepository<Cartao, String>{

	Optional<Cartao> findByCartaoStatus(StatusCartao statusCartao); 
	
}
