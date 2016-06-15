package br.com.fatec.projetos.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projetos.api.entity.ItemPedido;
import br.com.fatec.projetos.api.service.ItemPedidoDAO;
import br.com.fatec.projetos.core.helper.ConfigDBMapper;
import br.com.fatec.projetos.core.helper.ItemPedidoFactory;
import br.com.fatec.projetos.core.service.GeradorIdService;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class ItemPedidoDAOImpl implements ItemPedidoDAO{

	private Connection connection;
	private ItemPedidoFactory itemPedidoFactory;

	/** */
	public ItemPedidoDAOImpl() {
		this.itemPedidoFactory = new ItemPedidoFactory();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}

	@Override
	public ItemPedido save(ItemPedido itemPedido) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + ItemPedido.TABLE + " VALUES (?,?,?,?,?);");
			Long id = GeradorIdService.getInstance().nextId();
			insert.setLong(1, id);
			insert.setLong(2, itemPedido.getProduto().getId());
			insert.setLong(3, itemPedido.getPedido().getId());
			insert.setInt(4, itemPedido.getQuantidade());
			insert.setDouble(5, itemPedido.getValor());
			insert.execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar itemPedido.", e);
		}
	}

	@Override
	public void remove(ItemPedido itemPedido) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + ItemPedido.TABLE + " WHERE " + ItemPedido.COL_ID + " = ?;");
			remove.setLong(1, itemPedido.getId());
			remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover itemPedido " + itemPedido.getId(), e);
		}
	}

	@Override
	public List<ItemPedido> findAll() {
		PreparedStatement query = null;
		List<ItemPedido> itemPedidos = new ArrayList<ItemPedido>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + ItemPedido.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			itemPedidos = this.itemPedidoFactory.criarItemPedidos(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar itemPedidos.", e);
		}

		return itemPedidos;
	}

	@Override
	public ItemPedido findById(Long id) {
		PreparedStatement query = null;
		ItemPedido itemPedido = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + ItemPedido.TABLE + " WHERE " + ItemPedido.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				itemPedido = this.itemPedidoFactory.criarItemPedido(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar itemPedido.", e);
		}

		return itemPedido;
	}

	@Override
	public ItemPedido update(ItemPedido itemPedido) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + ItemPedido.TABLE + " SET " + ItemPedido.COL_PRODUTO + " = ?,"
					+ ItemPedido.COL_QUANTIDADE + " = ?," + ItemPedido.COL_VALOR + " = ? WHERE " + ItemPedido.COL_ID + " = ?;");
			update.setLong(1, itemPedido.getProduto().getId());
			update.setInt(2, itemPedido.getQuantidade());
			update.setDouble(3, itemPedido.getValor());
			update.setLong(4, itemPedido.getId());
			update.execute();
			return this.findById(itemPedido.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar itemPedido " + itemPedido.getId(), e);
		}
	}
}
