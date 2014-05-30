package br.com.kamaleon.dao.produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.kamaleon.util.ValidadorUniversal;

@Repository
public class ProdutoRepository {
	
	@PersistenceContext
	protected EntityManager entityManager;

	public List<Object[]> listarProdutosNovosQueNaoEstaoNoEcommerce(String codigoProduto, String dataInicial, String dataFinal){

		String sql = "SELECT * "
				+ " FROM VW_REL_PRODUTOS_ECOMMERCE_N "
				+ " where 1 = 1 ";
		
		if(ValidadorUniversal.check(codigoProduto))
		{
			sql += " and id_produto = " + codigoProduto;
		}
		if(ValidadorUniversal.check(dataInicial) && ValidadorUniversal.check(dataFinal) )
		{
			sql += " and dt_kardex between '" + dataInicial + "' and '" + dataFinal + "'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}

	public List<Object[]> listarProdutosComPrecoDeVendaDiferente(
			String codigoProduto, String referencia, String marca) {
		
		String sql = "SELECT * "
				+ " FROM Mv_produto_valor_diferente "
				+ " where 1 = 1 ";
		
		if(ValidadorUniversal.check(codigoProduto))
		{
			sql += " and id_produto = " + codigoProduto;
		}
		
		if(ValidadorUniversal.check(referencia))
		{
			sql += " and ds_referencia like '" + referencia.toUpperCase() + "%'";
		}
		
		if(ValidadorUniversal.check(marca))
		{
			sql += " and nm_marca like '" + marca.toUpperCase() + "%'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
		
	}
	
	public List<Object[]> listarProdutosComDiferencaEntreEstoqueKardex(String loja,
			String codigoProduto, String referencia, String marca) {
		
		String sql = "SELECT * "
				+ " FROM Mv_rel_estoque_x_kardex "
				+ " where 1 = 1 ";
		
		if(ValidadorUniversal.check(loja))
		{
			sql += " and id_loja = " + loja;
		}
		if(ValidadorUniversal.check(codigoProduto))
		{
			sql += " and id_produto = " + codigoProduto;
		}
		
		if(ValidadorUniversal.check(referencia))
		{
			sql += " and ds_referencia like '" + referencia.toUpperCase() + "%'";
		}
		
		if(ValidadorUniversal.check(marca))
		{
			sql += " and nm_marca like '" + marca.toUpperCase() + "%'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
		
	}

	public List<Object[]> listarProdutosComNomeDeImpressaoDiferente(
			String codigoProduto, String referencia, String marca) {

		String sql = "SELECT * "
				+ " FROM Vw_rel_nome_impressao_produto "
				+ " where 1 = 1 ";
		
		if(ValidadorUniversal.check(codigoProduto))
		{
			sql += " and id_produto = " + codigoProduto;
		}
		
		if(ValidadorUniversal.check(referencia))
		{
			sql += " and ds_referencia like '" + referencia.toUpperCase() + "%'";
		}
		
		if(ValidadorUniversal.check(marca))
		{
			sql += " and nm_marca like '" + marca.toUpperCase() + "%'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}

	public List<Object[]> listarReposicaoEstoque(String usuario,
			String dataInicial, String dataFinal) {
		
		String sql = "SELECT * "
				+ " FROM Vw_rel_incluir_reposic_estoque "
				+ " where 1 = 1 ";
		
		if(ValidadorUniversal.check(usuario))
		{
			sql += " and nm_usuario like '" + usuario.toUpperCase() + "%'";
		}
		
		if(ValidadorUniversal.check(dataInicial) && ValidadorUniversal.check(dataFinal) )
		{
			sql += " and dt_referencia between '" + dataInicial + "' and '" + dataFinal + "'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> listarRemessas(String lojaOrigem, String lojaDestino,
			String dataInicial, String dataFinal, String codigoRemessa,
			String remessaImpressa, String status) {
		
		String sql = "SELECT * "
				+ " FROM Vw_rel_remessas "
				+ " where 1 = 1 ";
		
		if(ValidadorUniversal.check(lojaOrigem))
		{
			sql += " and id_Loja_origem = " + lojaOrigem ;
		}
		if(ValidadorUniversal.check(lojaDestino))
		{
			sql += " and id_Loja_destino = " + lojaDestino ;
		}
		if(ValidadorUniversal.check(codigoRemessa))
		{
			sql += " and id_remessa = " + codigoRemessa ;
		}
		if(ValidadorUniversal.check(status))
		{
			sql += " and fl_status = '" + status + "'";
		}
		if(ValidadorUniversal.check(remessaImpressa))
		{
			sql += " and fl_impressa = '" + remessaImpressa + "'";
		}
		if(ValidadorUniversal.check(dataInicial) && ValidadorUniversal.check(dataFinal) )
		{
			sql += " and dt_remessa between '" + dataInicial + "' and '" + dataFinal + "'";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}

}