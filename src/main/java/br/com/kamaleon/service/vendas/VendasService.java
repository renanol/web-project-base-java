package br.com.kamaleon.service.vendas;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamaleon.dao.vendas.VendasRepository;

@Service
public class VendasService {
	
	@Autowired
	private VendasRepository dao;
	
	public List<Object[]> listarValorDeVendasProvenientesDeTrocasComCOO(String loja, String dataInicial, String dataFinal){
		return dao.listarValorDeVendasProvenientesDeTrocasComCOO(loja, dataInicial, dataFinal);
	}

	public List<Object[]> listarVendasComCOO(String loja, String dataInicial,
			String dataFinal) {
		return dao.listarVendasComCOO(loja, dataInicial, dataFinal);
	}

	public List<Object[]> listarVendasComTroca(String loja, String dataInicial,
			String dataFinal) {
		return dao.listarVendasComTroca(loja, dataInicial, dataFinal);
	}
	
	public List<Object[]> listarVendasPorCPF(String loja, String dataInicial,
			String dataFinal) {
		return dao.listarVendasPorCPF(loja, dataInicial, dataFinal);
	}
			
	public List<Object[]> listarTrocasDeCartaoValePresente(String loja, String dataInicial,
			String dataFinal) {
		return dao.listarTrocasDeCartaoValePresente(loja, dataInicial, dataFinal);
	}
	
	public HashMap getDadosGraficoVendaMensal(long codigoEstalecimento, String dataInicial, String dataFinal)
	{
		return dao.getDadosGraficoVendaMensal(codigoEstalecimento, dataInicial, dataFinal);
	}
	
}