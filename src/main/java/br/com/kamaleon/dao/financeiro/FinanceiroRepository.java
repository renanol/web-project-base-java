package br.com.kamaleon.dao.financeiro;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.kamaleon.util.ValidadorUniversal;

@Repository
public class FinanceiroRepository {
	
	@PersistenceContext
	protected EntityManager entityManager;


	public List<Object[]> listarRateiosDespesasDivergentes(
			int codigoTipoDespesa, int codigoGrupoDespesa,
			int codigoSubGrupoDespesa, String dataInicial, String dataFinal) {
		
		String sql = "SELECT * "
				+ " FROM Vw_rel_rateios_despesa_dif "
				+ " where 1 = 1 ";
		
		if(codigoTipoDespesa > 0)
		{
			sql += " and id_tipo_despesa = " + codigoTipoDespesa;
		}
		if(codigoGrupoDespesa > 0)
		{
			sql += " and id_grupo_despesa = " + codigoGrupoDespesa;
		}
		if(codigoSubGrupoDespesa > 0)
		{
			sql += " and id_sub_grupo_despesa = " + codigoSubGrupoDespesa;
		}
		if(ValidadorUniversal.check(dataInicial) && ValidadorUniversal.check(dataFinal) )
		{
			sql += " and dt_vencimento between '" + dataInicial + "' and '" + dataFinal + "'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
		
	}

}