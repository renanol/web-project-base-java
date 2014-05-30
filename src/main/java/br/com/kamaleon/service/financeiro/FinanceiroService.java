package br.com.kamaleon.service.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamaleon.dao.financeiro.FinanceiroRepository;
import br.com.kamaleon.dao.vendas.VendasRepository;

@Service
public class FinanceiroService {
	
	@Autowired
	private FinanceiroRepository dao;
	
	public List<Object[]> listarRateiosDespesasDivergentes(int codigoTipoDespesa, int codigoGrupoDespesa, int codigoSubGrupoDespesa, String dataInicial,
			String dataFinal) {
		return dao.listarRateiosDespesasDivergentes(codigoTipoDespesa, codigoGrupoDespesa, codigoSubGrupoDespesa, dataInicial, dataFinal);
	}
	
}