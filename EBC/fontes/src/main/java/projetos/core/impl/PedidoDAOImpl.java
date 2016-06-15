package br.com.fatec.projetos.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projetos.api.entity.Pedido;
import br.com.fatec.projetos.api.service.PedidoDAO;
import br.com.fatec.projetos.core.helper.ConfigDBMapper;
import br.com.fatec.projetos.core.helper.PedidoFactory;
import br.com.fatec.projetos.core.service.GeradorIdService;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class PedidoDAOImpl implements PedidoDAO{

	private Connection connection;
	private PedidoFactory pedidoFactory;

	/** */
	public PedidoDAOImpl() {
		this.pedidoFactory = new PedidoFactory();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}

	@Override
	public Pedido save(Pedido pedido) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Pedido.TABLE + " VALUES (?,?,?,?,?);");
			Long id = GeradorIdService.getInstance().nextId();
			insert.setLong(1, id);
			insert.setLong(2, pedido.getCliente().getId());
			insert.setLong(3, pedido.getFuncionario().getId());
			insert.setInt(4, pedido.getQuantidade());
			insert.setDouble(5, pedido.getTotal());
			insert.execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar pedido.", e);
		}
	}

	@Override
	public void remove(Pedido pedido) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Pedido.TABLE + " WHERE " + Pedido.COL_ID + " = ?;");
			remove.setLong(1, pedido.getId());
			remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover pedido " + pedido.getId(), e);
		}
	}

	@Override
	public List<Pedido> findAll() {
		PreparedStatement query = null;
		List<Pedido> pedidos = new ArrayList<Pedido>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Pedido.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			pedidos = this.pedidoFactory.criarPedidos(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar pedidos.", e);
		}

		return pedidos;
	}

	@Override
	public Pedido findById(Long id) {
		PreparedStatement query = null;
		Pedido pedido = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Pedido.TABLE + " WHERE " + Pedido.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				pedido = this.pedidoFactory.criarPedido(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar pedido.", e);
		}

		return pedido;
	}

	@Override
	public Pedido update(Pedido pedido) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Pedido.TABLE + " SET " + Pedido.COL_CLIENTE + " = ?,"
				+ Pedido.COL_FUNCIONARIO + " = ?," + Pedido.COL_QUANTIDADE + " = ?," + Pedido.COL_TOTAL + " = ? WHERE " + Pedido.COL_ID + " = ?;");
			update.setLong(1, pedido.getCliente().getId());
			update.setLong(2, pedido.getFuncionario().getId());
			update.setInt(3, pedido.getQuantidade());
			update.setDouble(4, pedido.getTotal());
			update.setLong(5, pedido.getId());
			update.execute();
			return this.findById(pedido.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar pedido " + pedido.getId(), e);
		}
	}
}
