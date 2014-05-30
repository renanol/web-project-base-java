package br.com.kamaleon.dao.fidelidade;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.kamaleon.util.ValidadorUniversal;

@Repository
public class FidelidadeRepository {
	
	@PersistenceContext
	protected EntityManager entityManager;

	public List<Object[]> listarPontuacaoFidelidadePorFaixa(String codigoPromocao){

		String sql = "SELECT * "
				+ " FROM Vw_rel_pontos_fidelidade";
		
		if(ValidadorUniversal.check(codigoPromocao))
		{
			sql += " where id_promocao = " + codigoPromocao;
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> listarPremiosRetiradosNoFestClub(String dataInicial, String dataFinal){

		String sql = "SELECT * "
				+ " FROM   Vw_rel_premios_resgatados"
				+ " WHERE  "
				+ " dt_resgate between '"+dataInicial+"' and '"+dataFinal+"'";
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> listarFidelidadeMegaCards(String nome, String cpfCnpj, String diferenca){
		
		String sql = "SELECT * "
				+ " FROM   Vw_rel_fidelidade_mega_cards"
				+ " WHERE  1 = 1";
				
		if(ValidadorUniversal.check(nome))
		{
			sql += " and nm_cliente like '" + nome.toUpperCase() + "%'";
		}
		if(ValidadorUniversal.check(cpfCnpj))
		{
			sql += " and nu_cpf_cnpj = '" + cpfCnpj + "'";
		}
		if(ValidadorUniversal.check(diferenca))
		{
			sql += " and fl_existe_diferenca = '" + diferenca + "'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	

}