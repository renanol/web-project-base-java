package br.com.kamaleon.dao.vendas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.kamaleon.util.FuncoesNumeros;
import br.com.kamaleon.util.ValidadorUniversal;

@Repository
public class VendasRepository {
	
	@PersistenceContext
	protected EntityManager entityManager;

	public List<Object[]> listarValorDeVendasProvenientesDeTrocasComCOO(String loja, String dataInicial, String dataFinal){

		String sql = "SELECT * "
				+ " FROM Vw_rel_total_vendas_troca_coo "
				+ " where 1 = 1 ";
		
		if(ValidadorUniversal.check(loja))
		{
			sql += " and id_loja = " + loja ;
		}
		if(ValidadorUniversal.check(dataInicial) && ValidadorUniversal.check(dataFinal) )
		{
			sql += " and dt_referencia between '" + dataInicial + "' and '" + dataFinal + "'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}

	public List<Object[]> listarVendasComCOO(String loja, String dataInicial,
			String dataFinal) {
		
		String sql = "SELECT * "
				+ " FROM Vw_rel_vendas_com_cupom_fiscal "
				+ " where 1 = 1 ";
		
		if(ValidadorUniversal.check(loja))
		{
			sql += " and id_loja = " + loja ;
		}
		if(ValidadorUniversal.check(dataInicial) && ValidadorUniversal.check(dataFinal) )
		{
			sql += " and dt_referencia between '" + dataInicial + "' and '" + dataFinal + "'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> listarVendasPorCPF(String loja, String dataInicial,
			String dataFinal) {
		
		String sql = "SELECT * "
				+ " FROM Vw_rel_total_vendas_por_cpf "
				+ " where 1 = 1 ";
		
		if(ValidadorUniversal.check(loja))
		{
			sql += " and id_Loja = " + loja ;
		}
		if(ValidadorUniversal.check(dataInicial) && ValidadorUniversal.check(dataFinal) )
		{
			sql += " and dt_venda between '" + dataInicial + "' and '" + dataFinal + "'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> listarTrocasDeCartaoValePresente(String loja, String dataInicial,
			String dataFinal) {
		
		String sql = "SELECT * "
				+ " FROM Vw_rel_trocas_cartao_presente "
				+ " where 1 = 1 ";
		
		if(ValidadorUniversal.check(loja))
		{
			sql += " and id_Loja = " + loja ;
		}
		if(ValidadorUniversal.check(dataInicial) && ValidadorUniversal.check(dataFinal) )
		{
			sql += " and dt_troca between '" + dataInicial + "' and '" + dataFinal + "'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}

	public List<Object[]> listarVendasComTroca(String loja, String dataInicial,
			String dataFinal) {
		
		String sql = "SELECT * "
				+ " FROM VW_REL_VENDAS_COM_TROCA "
				+ " where 1 = 1 ";
		
		if(ValidadorUniversal.check(loja))
		{
			sql += " and id_loja = " + loja ;
		}
		if(ValidadorUniversal.check(dataInicial) && ValidadorUniversal.check(dataFinal) )
		{
			sql += " and dt_venda between '" + dataInicial + "' and '" + dataFinal + "'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public HashMap getDadosGraficoVendaMensal(long codigoEstalecimento, String dataInicial, String dataFinal)  {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(										
				"SELECT sum(v.totalcompra), to_char(v.data, 'mm/yyyy'), e.sigla"
				+ " from t_venda v, t_pontodevenda pdv , t_estabelecimento e"
				+ " where"
				+ " v.idpontodevenda = pdv.idpontodevenda and"
				+ " pdv.idestabelecimento = e.idestabelecimento and"
				+ " v.data between '"+dataInicial+"' and '"+dataFinal+"' and"
				+ " v.status = 'A'"
				);
		
		if(codigoEstalecimento > 0)
		{
			sql.append(										
					" and e.idestabelecimento = " + codigoEstalecimento
					);
		}
		
		sql.append(" group by to_char(v.data, 'mm/yyyy'), e.sigla order by 3, to_date('01/' || to_char(v.data, 'mm/yyyy'), 'dd/mm/yyyy')");
		
		List<Object[]> query = entityManager.createNativeQuery(sql.toString()).getResultList();
		
		List<String> datas = new ArrayList();
		
		for(Object[] objeto : query)
		{
			if(!datas.contains(objeto[1]))
			{
				datas.add((String)objeto[1]);
			}
		}

		ArrayList<HashMap> listaHas = new ArrayList<HashMap>();
		ArrayList<String> listaEstabelecimento = new ArrayList<String>();
//		preenchendo apenas os estabelecimentos
		for(Object[] objeto : query)
		{
			if(!listaEstabelecimento.contains(objeto[2]))
			{
				listaEstabelecimento.add((String)objeto[2]);
			}
		}
//		preenchendo o hash
		for(String estabelecimento : listaEstabelecimento)
		{
			HashMap hash = new HashMap();
			
			hash.put("name", estabelecimento);
			
			Double[] valores = new Double[datas.size()];
			
			for(int i = 0; i < datas.size(); i++)
			{
				valores[i] = 0d;
				
				for(Object[] objeto : query)
				{
					if(objeto[1].equals(datas.get(i)) && estabelecimento.equals(objeto[2]))
					{
						valores[i] = ((BigDecimal) objeto[0]).doubleValue();
					}
				}
			}
			hash.put("data", valores);
			listaHas.add(hash);
		}
		
		HashMap a = new HashMap();
		a.put("datas", datas);
		a.put("dados", listaHas);
		
		return   a;
	}
	
}