package br.com.kamaleon.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.kamaleon.dao.UtilRepository;

/**
 * Created by @author Vinicius on 25/05/14
 */
@Service
public class UtilService {

    @Resource
    private UtilRepository dao;
    
    public List<Object[]> listarEstabelecimentosAtivos(){
    	return dao.listarEstabelecimentosAtivos();
    }
    
    public List<Object[]> listarTipoDespesa(){
    	return dao.listarTipoDespesaAtivos();
    }
    
    public List<Object[]> listarGrupoDespesa(int codigoTipoDespesa){
    	return dao.listarGrupoDespesaAtivos(codigoTipoDespesa);
    }
    
    public List<Object[]> listarSubGrupoDespesa(int codigoGrupoDespesa){
    	return dao.listarSubGrupoDespesaAtivos(codigoGrupoDespesa);
    }
	
}
