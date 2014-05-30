package br.com.kamaleon.dao.cliente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.kamaleon.util.ValidadorUniversal;

@Repository
public class ClienteRepository {
	
	@PersistenceContext
	protected EntityManager entityManager;


	public List<Object[]> listarClienteSemFestClub(String nome, String cpfcnpj) {
		
		String sql = "SELECT * "
				+ " FROM Vw_rel_clientes_sem_festclub "
				+ " where 1 = 1 ";
		
		if(ValidadorUniversal.check(nome))
		{
			sql += " and nm_cliente  like '" + nome.toUpperCase() + "%'";
		}
		if(ValidadorUniversal.check(cpfcnpj))
		{
			sql += " and nm_cliente  = '" + cpfcnpj.toUpperCase() + "'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> listarCadastroClientesCardsMega(String nome, String cpfCnpj, String existeMega, String existeCards){
		
		String sql = "SELECT * "
				+ " FROM   Vw_rel_cadastro_clientes_cards"
				+ " WHERE  1 = 1";
				
		if(ValidadorUniversal.check(nome))
		{
			sql += " and nm_cliente like '" + nome.toUpperCase() + "%'";
		}
		if(ValidadorUniversal.check(cpfCnpj))
		{
			sql += " and nu_cpf_cnpj = '" + cpfCnpj + "'";
		}
		if(ValidadorUniversal.check(existeMega))
		{
			sql += " and fl_existe_mega = '" + existeMega + "'";
		}
		if(ValidadorUniversal.check(existeCards))
		{
			sql += " and fl_existe_cards = '" + existeCards + "'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> listarClientesComMaisDeUmCartaoNoCards(String nome,
			String cpfcnpj, String numeroCartao, String statusCliente,
			String statusCartao, String nomeDependente,
			String numeroCartaoDependente, String statusCartaoDependente,
			String statusCartaoTitular) {
		
		String sql = "SELECT * "
				+ " FROM   Vw_rel_clientes_cartoes_cards2"
				+ " WHERE  1 = 1";
				
		if(ValidadorUniversal.check(nome))
		{
			sql += " and nm_cliente like '" + nome.toUpperCase() + "%'";
		}
		if(ValidadorUniversal.check(nomeDependente))
		{
			sql += " and nm_cliente_dep like '" + nomeDependente.toUpperCase() + "%'";
		}
		if(ValidadorUniversal.check(cpfcnpj))
		{
			sql += " and nu_cpf = '" + cpfcnpj + "'";
		}
		if(ValidadorUniversal.check(numeroCartao))
		{
			sql += " and nu_cartao = '" + numeroCartao+ "'";
		}
		if(ValidadorUniversal.check(numeroCartaoDependente))
		{
			sql += " and nu_cartao_dep = '" + numeroCartaoDependente + "'";
		}
		if(ValidadorUniversal.check(statusCliente))
		{
			sql += " and fl_status_cliente = '" + statusCliente + "'";
		}
		if(ValidadorUniversal.check(statusCartao))
		{
			sql += " and fl_status_cartao = '" + statusCartao + "'";
		}
		if(ValidadorUniversal.check(statusCartaoDependente))
		{
			sql += " and fl_status_cartao_dep = '" + statusCartaoDependente + "'";
		}
		if(ValidadorUniversal.check(statusCartaoDependente))
		{
			sql += " and fl_status_titular_dep = '" + statusCartaoTitular + "'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}

}