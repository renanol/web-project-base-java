package br.com.kamaleon.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class UtilRepository {
	
	@PersistenceContext
	protected EntityManager entityManager;

	public List<Object[]> listarEstabelecimentosAtivos(){

		String sql = "SELECT idestabelecimento, nomefantasia, sigla "
				+ " FROM   t_estabelecimento"
				+ " WHERE  "
				+ " status = 'A' and idestabelecimento > 0"
				+ " order by nomefantasia";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> listarTipoDespesaAtivos(){
		
		String sql = "SELECT idtipoDespesa, descricao "
				+ " FROM   t_tipoDespesa"
				+ " WHERE  "
				+ " status = 'A' order by descricao asc";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> listarGrupoDespesaAtivos(int codigo){
		
		String sql = "SELECT idGrupoDespesa, descricao "
				+ " FROM   t_GrupoDespesa"
				+ " WHERE  "
				+ " status = 'A' ";
		
		if(codigo > 0)
		{
			sql += " and idtipodespesa = " + codigo;
		}
		
		sql += " order by descricao asc";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> listarSubGrupoDespesaAtivos(int codigo){
		
		String sql = "SELECT idsubGrupoDespesa, descricao "
				+ " FROM   t_subGrupoDespesa"
				+ " WHERE  "
				+ " status = 'A' ";
		
		if(codigo > 0)
		{
			sql += " and idgrupodespesa = " + codigo;
		}
		
		sql += " order by descricao asc";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
}