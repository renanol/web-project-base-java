package br.com.kamaleon.service.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamaleon.dao.cliente.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository dao;
	
	public List<Object[]> listarClienteSemFestClub(String nome, String cpfcnpj) {
		// TODO Auto-generated method stub
		return dao.listarClienteSemFestClub(nome,cpfcnpj);
	}

	public List<Object[]> listarCadastroClientesCardsMega(String nome,
			String cpfcnpj, String existeMega, String existeCards) {
		// TODO Auto-generated method stub
		return dao.listarCadastroClientesCardsMega(nome, cpfcnpj, existeMega, existeCards);
	}

	public List<Object[]> listarClientesComMaisDeUmCartaoNoCards(String nome,
			String cpfcnpj, String numeroCartao, String statusCliente,
			String statusCartao, String nomeDependente,
			String numeroCartaoDependente, String statusCartaoDependente,
			String statusCartaoTitular) {
		// TODO Auto-generated method stub
		return dao.listarClientesComMaisDeUmCartaoNoCards(nome, cpfcnpj, numeroCartao, statusCliente, statusCartao, nomeDependente,
				numeroCartaoDependente, statusCartaoDependente, statusCartaoTitular);
	}
	
}