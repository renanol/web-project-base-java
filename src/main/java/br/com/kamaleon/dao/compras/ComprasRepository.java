package br.com.kamaleon.dao.compras;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.kamaleon.util.ValidadorUniversal;

@Repository
public class ComprasRepository {
	
	@PersistenceContext
	protected EntityManager entityManager;

	public List<Object[]> listarNotasFiscaisDuplicadas(String dataInicial, String dataFinal, String codigoLoja){

		String sql = "SELECT * "
				+ " FROM vw_rel_nota_fiscal_duplicada"
				+ " WHERE  "
				+ " DT_NOTA between '"+dataInicial+"' and '"+dataFinal+"'";
		
		if(ValidadorUniversal.check(codigoLoja))
		{
			sql += " and id_loja = " + codigoLoja;
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	

}