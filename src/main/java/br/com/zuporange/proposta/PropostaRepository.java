package br.com.zuporange.proposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta, String>{

	Optional<Proposta> findByDocumento(String documento);

	List<Proposta> findByStatus(Status elegivel);
	
	
}
