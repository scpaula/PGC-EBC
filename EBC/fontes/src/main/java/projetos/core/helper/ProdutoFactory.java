package br.com.fatec.projetos.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projetos.api.entity.Produto;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class ProdutoFactory {

	/**
	 * @param id
	 * @param nome
	 * @param marca
	 * @param preco
	 * @param quantidade
	 * @return o {@link Produto} criado a partir dos parametros passados
	 */
	public Produto criarProduto(Long id , String nome, String marca, double preco, int quantidade) {
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome(nome);
		produto.setMarca(marca);
		produto.setPreco(preco);
		produto.seQuantidade(quantidade);
		return produto;
	}
	
	public Produto criarProduto(ResultSet resultado) {
		try {
			return this.criarProduto(resultado.getLong(Produto.COL_ID), resultado.getString(Produto.COL_NOME),
					resultado.getString(Produto.COL_MARCA),resultado.getDouble(Produto.COL_PRECO) , resultado.getInt(Produto.COL_QUANTIDADE));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar Produto.", e);
		}
	}
	
	public List<Produto> criarProdutos(ResultSet resultado) {
		try {
			List<Produto> produtos = new ArrayList<Produto>();
			while (resultado.next()) {
				produtos.add(this.criarProduto(resultado));
			}
			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar Produto.", e);
		}
	}

	
	
}
