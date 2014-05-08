package br.com.kamaleon.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class DecredRepository {
	
	@PersistenceContext
	protected EntityManager entityManager;

	public List<Object[]> decredTotalEncargosAtrasoSintetico(String dataInicial, String dataFinal){

		String sql = "SELECT * "
				+ " FROM   VW_REL_ENCARGOS_ATRASO_SINTET"
				+ " WHERE  "
				+ " dt_referencia between '"+dataInicial+"' and '"+dataFinal+"'";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> decredTotalEncargosAtrasoAnalitico(String dataInicial, String dataFinal){
		
		String sql = "SELECT * "
				+ " FROM   VW_REL_ENCARGOS_ATRASO_ANALIT"
				+ " WHERE  "
				+ " dt_referencia between '"+dataInicial+"' and '"+dataFinal+"'";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> decredMultaSobreAtrasoSintetico(String dataInicial, String dataFinal){
		
		String sql = "SELECT * "
				+ " FROM   VW_REL_MULTAS_ATRASO_SINTET"
				+ " WHERE  "
				+ " dt_referencia between '"+dataInicial+"' and '"+dataFinal+"'";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> decredMultaSobreAtrasoAnalitico(String dataInicial, String dataFinal){
		
		String sql = "SELECT * "
				+ " FROM   VW_REL_MULTAS_ATRASO_ANALIT"
				+ " WHERE  "
				+ " dt_referencia between '"+dataInicial+"' and '"+dataFinal+"'";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> decredTaxaDeAdministracaoSintetico(String dataInicial, String dataFinal){
		
		String sql = "SELECT * "
				+ " FROM   VW_REL_TAXA_ADM_SINTET"
				+ " WHERE  "
				+ " dt_referencia between '"+dataInicial+"' and '"+dataFinal+"'";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> decredTaxaDeAdministracaoAnalitico(String dataInicial, String dataFinal){
		
		String sql = "SELECT * "
				+ " FROM   VW_REL_TAXA_ADM_ANALIT"
				+ " WHERE  "
				+ " dt_referencia between '"+dataInicial+"' and '"+dataFinal+"'";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> decredParcelaDeAnuidadeSintetico(String dataInicial, String dataFinal){
		
		String sql = "SELECT * "
				+ " FROM   VW_REL_PARC_ANUIDADE_SINTET"
				+ " WHERE  "
				+ " dt_referencia between '"+dataInicial+"' and '"+dataFinal+"'";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> decredParcelaDeAnuidadeAnalitico(String dataInicial, String dataFinal){
		
		String sql = "SELECT * "
				+ " FROM   VW_REL_PARC_ANUIDADE_ANALIT"
				+ " WHERE  "
				+ " dt_referencia between '"+dataInicial+"' and '"+dataFinal+"'";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}

	public List<Object[]> decredEncargosDeCobrancaPorDescontoSintetico(
			String dataInicial, String dataFinal) {
		String sql = "SELECT * "
				+ " FROM   VW_REL_ENCARGOS_COBRANC_SINTET"
				+ " WHERE  "
				+ " dt_referencia between '"+dataInicial+"' and '"+dataFinal+"'";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}

	public List<Object[]> decredEncargosDeCobrancaPorDescontoAnalitico(
			String dataInicial, String dataFinal) {
		String sql = "SELECT * "
				+ " FROM   VW_REL_ENCARGOS_COBRANC_ANALIT"
				+ " WHERE  "
				+ " dt_referencia between '"+dataInicial+"' and '"+dataFinal+"'";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}

}