package br.com.kamaleon.service.decred;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamaleon.dao.DecredRepository;
import br.com.kamaleon.model.User;

@Service
public class DecredService {
	
	@Autowired
	private DecredRepository dao;
	
	public boolean login( String  username, String password ) {
		
		List<User> u = null;//userDao.findUsersByUsernameAndPassword(username, password);
		
		if ( u.size() == 1 ) return true;
		else return false;
		
	}
	
	public List<Object[]> decredTotalEncargosAtrasoSintetico(String dataInicial, String dataFinal){
		return dao.decredTotalEncargosAtrasoSintetico(dataInicial, dataFinal);
	}
	
	public List<Object[]> decredTotalEncargosAtrasoAnalitico(String dataInicial, String dataFinal){
		return dao.decredTotalEncargosAtrasoAnalitico(dataInicial, dataFinal);
	}
	
	
//	Multa sobre atraso 
	public List<Object[]> decredMultaSobreAtrasoSintetico(String dataInicial, String dataFinal){
		return dao.decredMultaSobreAtrasoSintetico(dataInicial, dataFinal);
	}
	public List<Object[]> decredMultaSobreAtrasoAnalitico(String dataInicial, String dataFinal){
		return dao.decredMultaSobreAtrasoAnalitico(dataInicial, dataFinal);
	}

	public List<Object[]> decredTaxaDeAdministracaoSintetico(
			String dataInicial, String dataFinal) {
		// TODO Auto-generated method stub
		return dao.decredTaxaDeAdministracaoSintetico(dataInicial, dataFinal);
	}

	public List<Object[]> decredTaxaDeAdministracaoAnalitico(
			String dataInicial, String dataFinal) {
		// TODO Auto-generated method stub
		return dao.decredTaxaDeAdministracaoAnalitico(dataInicial, dataFinal);
	}

	public List<Object[]> decredParcelaDeAnuidadeAnalitico(String dataInicial,
			String dataFinal) {
		// TODO Auto-generated method stub
		return dao.decredParcelaDeAnuidadeAnalitico(dataInicial, dataFinal);
	}

	public List<Object[]> decredParcelaDeAnuidadeSintetico(String dataInicial,
			String dataFinal) {
		// TODO Auto-generated method stub
		return dao.decredParcelaDeAnuidadeSintetico(dataInicial, dataFinal);
	}

	public List<Object[]> decredEncargosDeCobrancaPorDescontoSintetico(
			String dataInicial, String dataFinal) {
		// TODO Auto-generated method stub
		return dao.decredEncargosDeCobrancaPorDescontoSintetico(dataInicial,  dataFinal);
	}

	public List<Object[]> decredEncargosDeCobrancaPorDescontoAnalitico(
			String dataInicial, String dataFinal) {
		// TODO Auto-generated method stub
		return dao.decredEncargosDeCobrancaPorDescontoAnalitico(dataInicial,  dataFinal);
	}
	
	
}