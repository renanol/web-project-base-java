package br.com.kamaleon.service.fidelidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamaleon.dao.fidelidade.FidelidadeRepository;

@Service
public class FidelidadeService {
	
	@Autowired
	private FidelidadeRepository dao;
	
	public List<Object[]> listarPontuacaoFidelidadePorFaixa(String codigoPromocao){
		return dao.listarPontuacaoFidelidadePorFaixa(codigoPromocao);
	}

	public List<Object[]> listarPremiosRetiradosNoFestClub(String dataInicial,
			String dataFinal) {
		// TODO Auto-generated method stub
		return dao.listarPremiosRetiradosNoFestClub(dataInicial, dataFinal);
	}
	
	public List<Object[]> listarFidelidadeMegaCards(String nome, String cpfCnpj, String diferenca) {
		// TODO Auto-generated method stub
		return dao.listarFidelidadeMegaCards(nome, cpfCnpj, diferenca);
	}
	
}