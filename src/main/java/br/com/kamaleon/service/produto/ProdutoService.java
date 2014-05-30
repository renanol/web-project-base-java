package br.com.kamaleon.service.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamaleon.dao.produto.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository dao;
	
	public List<Object[]> listarProdutosNovosQueNaoEstaoNoEcommerce(String codigoProduto , String dataInicial, String dataFinal){
		return dao.listarProdutosNovosQueNaoEstaoNoEcommerce(codigoProduto, dataInicial, dataFinal);
	}

	public List<Object[]> listarProdutosComPrecoDeVendaDiferente(
			String codigoProduto, String referencia, String marca) {
		return dao.listarProdutosComPrecoDeVendaDiferente(codigoProduto, referencia, marca);
	}
	
	public List<Object[]> listarProdutosComDiferencaEntreEstoqueKardex(String loja,
			String codigoProduto, String referencia, String marca) {
		return dao.listarProdutosComDiferencaEntreEstoqueKardex(loja, codigoProduto, referencia, marca);
	}

	public List<Object[]> listarProdutosComNomeDeImpressaoDiferente(
			String codigoProduto, String referencia, String marca) {
		// TODO Auto-generated method stub
		return dao.listarProdutosComNomeDeImpressaoDiferente(codigoProduto, referencia, marca);
	}

	public List<Object[]> listarReposicaoEstoque(String usuario,
			String dataInicial, String dataFinal) {
		// TODO Auto-generated method stub
		return dao.listarReposicaoEstoque(usuario, dataInicial, dataFinal);
	}

	public List<Object[]> listarRemessas(String lojaOrigem, String lojaDestino,
			String dataInicial, String dataFinal, String codigoRemessa,
			String remessaImpressa, String status) {
		// TODO Auto-generated method stub
		return dao.listarRemessas(lojaOrigem, lojaDestino, dataInicial, dataFinal, codigoRemessa, remessaImpressa, status);
	}
	
}