package br.com.kamaleon.service.compras;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamaleon.dao.compras.ComprasRepository;
import br.com.kamaleon.model.User;

@Service
public class ComprasService {
	
	@Autowired
	private ComprasRepository dao;
	
	public List<Object[]> listarNotasFiscaisDuplicadas(String dataInicial, String dataFinal, String codigoLoja){
		return dao.listarNotasFiscaisDuplicadas(dataInicial, dataFinal, codigoLoja);
	}
	
}