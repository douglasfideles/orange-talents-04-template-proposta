package br.com.zuporange.proposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta, String>{

	Optional<Proposta> findByDocumento(String documento);

	//List<Proposta> findByStatus(Status elegivel);
	@Query("select p from Proposta p where p.status = :status and p.cartao is null")
	List<Proposta> findByStatusOndeCartaoElegivel(Status status);
	
}
