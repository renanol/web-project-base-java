package br.com.kamaleon.dao.cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.kamaleon.model.cliente.Cliente;

@Repository
public class ClienteRepository {
	
	@PersistenceContext
	protected EntityManager entityManager;

	public Cliente cadastrar(Cliente cliente){
		
		entityManager.persist(cliente);
		
		return cliente;
	}


}