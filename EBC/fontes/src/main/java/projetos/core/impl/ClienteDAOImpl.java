package br.com.fatec.projetos.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projetos.api.entity.Cliente;
import br.com.fatec.projetos.api.service.ClienteDAO;
import br.com.fatec.projetos.core.helper.CargoFactory;
import br.com.fatec.projetos.core.helper.ClienteFactory;
import br.com.fatec.projetos.core.helper.ConfigDBMapper;
import br.com.fatec.projetos.core.service.GeradorIdService;



/**
 * @author Sonia
 *
 * @version 1.0.0
 */
public class ClienteDAOImpl implements ClienteDAO{

	private Connection connection;
	private ClienteFactory clienteFactory;

	/** */
	public ClienteDAOImpl() {
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
		clienteFactory = new ClienteFactory();
	}

	@Override
	public Cliente save(Cliente cliente) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Cliente.TABLE + " VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			Long id = GeradorIdService.getInstance().nextId();
			insert.setLong(1, id);
			insert.setString(2, cliente.getNome());
			insert.setString(3, cliente.getSobrenome());
			insert.setString(4, cliente.getEmail());
			insert.setString(5, cliente.getCPF());
			insert.setString(6, cliente.getTel());
			insert.setString(7, cliente.getEndereco());
			insert.setString(8, cliente.getBairro());
			insert.setString(9, cliente.getCidade());
			insert.setString(10, cliente.getEstado());
			insert.setString(11, cliente.getSenha());
			insert.execute();
			insert = this.connection.prepareStatement("COMMIT");
			insert.execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar cliente.", e);
		}
	}

	@Override
	public void remove(Cliente cliente) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Cliente.TABLE + " WHERE " + Cliente.COL_ID + " = ?;");
			remove.setLong(1, cliente.getId());
			remove.execute();
			remove = this.connection.prepareStatement("COMMIT");
			remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover cliente " + cliente.getNome(), e);
		}
	}

	@Override
	public List<Cliente> findAll() {
		PreparedStatement query = null;
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Cliente.TABLE);
			ResultSet resultado = query.executeQuery();
			clientes = this.clienteFactory.criarClientes(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar clientes.", e);
		}

		return clientes;
	}

	@Override
	public Cliente findById(Long id) {
		PreparedStatement query = null;
		Cliente cliente = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Cliente.TABLE + " WHERE " + Cliente.COL_ID
					+ " = ?");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				cliente = this.clienteFactory.criarCliente(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar cliente.", e);
		}

		return cliente;
	}

	@Override
	public Cliente update(Cliente cliente) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement( "UPDATE " + Cliente.TABLE + " SET " + Cliente.COL_NOME + " = ?," + Cliente.COL_SOBRENOME + " = ?," +
														Cliente.COL_EMAIL + " = ?," + Cliente.COL_CPF + " = ?," + Cliente.COL_TEL + " = ?," + Cliente.COL_ENDERECO + " = ?," +
														Cliente.COL_BAIRRO + " = ?," + Cliente.COL_CIDADE + " = ?,"	+ Cliente.COL_ESTADO + " = ?," + Cliente.COL_SENHA + " = ?" +
														"WHERE " + Cliente.COL_ID + " = ?;");
			
			update.setString(1, cliente.getNome());
			update.setString(2, cliente.getSobrenome());
			update.setString(3, cliente.getEmail());
			update.setString(4, cliente.getCPF());
			update.setString(5, cliente.getTel());
			update.setString(6, cliente.getEndereco());
			update.setString(7, cliente.getBairro());
			update.setString(8, cliente.getCidade());
			update.setString(9, cliente.getEstado());
			update.setString(10, cliente.getSenha());
			update.setLong(11, cliente.getId());
			update.execute();
			return this.findById(cliente.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar cliente " + cliente.getNome(), e);
		}
	}
}
