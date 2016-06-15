package br.com.fatec.projetos.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projetos.api.entity.Produto;
import br.com.fatec.projetos.api.service.ProdutoDAO;
import br.com.fatec.projetos.core.helper.ConfigDBMapper;
import br.com.fatec.projetos.core.helper.ProdutoFactory;
import br.com.fatec.projetos.core.service.GeradorIdService;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class ProdutoDAOImpl implements ProdutoDAO{
	
	private Connection connection;
	private ProdutoFactory produtoFactory;

	/** */
	public ProdutoDAOImpl() {
		this.produtoFactory = new ProdutoFactory();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}

	@Override
	public Produto save(Produto produto) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Produto.TABLE + " VALUES (?,?,?,?,?);");
			Long id = GeradorIdService.getInstance().nextId();
			insert.setLong(1, id);
			insert.setString(2, produto.getNome());
			insert.setDouble(3, produto.getPreco());
			insert.setInt(4, produto.getQuantidade());
			insert.setString(5, produto.getMarca());
			
			insert.execute();
			
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar produto.", e);
		}
	}

	@Override
	public void remove(Produto produto) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Produto.TABLE + " WHERE " + Produto.COL_ID + " = ?;");
			remove.setLong(1, produto.getId());
			remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover produto " + produto.getNome(), e);
		}
	}

	@Override
	public List<Produto> findAll() {
		PreparedStatement query = null;
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Produto.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			produtos = this.produtoFactory.criarProdutos(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar produtos.", e);
		}

		return produtos;
	}

	@Override
	public Produto findById(Long id) {
		PreparedStatement query = null;
		Produto produto = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Produto.TABLE + " WHERE " + Produto.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				produto = this.produtoFactory.criarProduto(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar produto.", e);
		}

		return produto;
	}

	@Override
	public Produto update(Produto produto) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Produto.TABLE + " SET " + Produto.COL_NOME + " = ?,"
					+ Produto.COL_MARCA + " = ?," + Produto.COL_PRECO + " = ?," + Produto.COL_QUANTIDADE + " = ? WHERE " + Produto.COL_ID + " = ?;");
			update.setString(1, produto.getNome());
			update.setString(2, produto.getMarca());			
			update.setDouble(3, produto.getPreco());
			update.setInt(4, produto.getQuantidade());
			update.setLong(5, produto.getId());
			update.execute();
			return this.findById(produto.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar produto " + produto.getNome(), e);
		}
	}
}
