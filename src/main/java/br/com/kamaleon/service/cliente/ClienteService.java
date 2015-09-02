package br.com.kamaleon.service.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamaleon.dao.cliente.ClienteRepository;
import br.com.kamaleon.model.cliente.Cliente;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	

	public Cliente cadastrar(Cliente cliente){
		return repository.cadastrar(cliente);
	} 
	


	
}