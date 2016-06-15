package br.com.fatec.projetos.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projetos.api.entity.Funcionario;
import br.com.fatec.projetos.api.service.FuncionarioDAO;
import br.com.fatec.projetos.core.helper.ConfigDBMapper;
import br.com.fatec.projetos.core.helper.FuncionarioFactory;
import br.com.fatec.projetos.core.service.GeradorIdService;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class FuncionarioDAOImpl implements FuncionarioDAO{

	private Connection connection;
	private FuncionarioFactory funcionarioFactory;

	/** */
	public FuncionarioDAOImpl() {
		this.funcionarioFactory = new FuncionarioFactory();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}

	@Override
	public Funcionario save(Funcionario funcionario) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Funcionario.TABLE + " VALUES (?,?,?,?,?);");
			Long id = GeradorIdService.getInstance().nextId();
			insert.setLong(1, id);
			insert.setString(2, funcionario.getNome());
			insert.setString(3, funcionario.getCPF());
			insert.setString(4, funcionario.getTel());
			insert.setLong(5, funcionario.getCargo().getId());
			insert.execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar funcionario.", e);
		}
	}

	@Override
	public void remove(Funcionario funcionario) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Funcionario.TABLE + " WHERE " + Funcionario.COL_ID + " = ?;");
			remove.setLong(1, funcionario.getId());
			remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover funcionario " + funcionario.getNome(), e);
		}
	}

	@Override
	public List<Funcionario> findAll() {
		PreparedStatement query = null;
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Funcionario.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			funcionarios = this.funcionarioFactory.criarFuncionarios(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar funcionarios.", e);
		}

		return funcionarios;
	}

	@Override
	public Funcionario findById(Long id) {
		PreparedStatement query = null;
		Funcionario funcionario = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Funcionario.TABLE + " WHERE " + Funcionario.COL_ID
					+ " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				funcionario = this.funcionarioFactory.criarFuncionario(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar funcionario.", e);
		}
		return funcionario;
	}

	@Override
	public Funcionario update(Funcionario funcionario) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Funcionario.TABLE + " SET " + Funcionario.COL_NOME + " = ?,"
					+ Funcionario.COL_CPF + " = ?," + Funcionario.COL_TEL + " = ?," + Funcionario.COL_CARGO + " = ? WHERE " + Funcionario.COL_ID + " = ?;");
			update.setString(1, funcionario.getNome());
			update.setString(2, funcionario.getCPF());
			update.setString(3, funcionario.getTel());
			update.setLong(4, funcionario.getCargo().getId());
			update.setLong(5, funcionario.getId());
			update.execute();
			return this.findById(funcionario.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar funcionario " + funcionario.getNome(), e);
		}
	}
}
