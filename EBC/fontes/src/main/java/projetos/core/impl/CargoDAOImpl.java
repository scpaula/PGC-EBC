package br.com.fatec.projetos.core.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projetos.api.entity.Cargo;
import br.com.fatec.projetos.api.service.CargoDAO;
import br.com.fatec.projetos.core.helper.CargoFactory;
import br.com.fatec.projetos.core.helper.ConfigDBMapper;
import br.com.fatec.projetos.core.service.GeradorIdService;


/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class CargoDAOImpl implements CargoDAO{

	private Connection connection;
	private CargoFactory cargoFactory;

	/** */
	public CargoDAOImpl() {
		this.cargoFactory = new CargoFactory();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}
	
	@Override
	public Cargo save(Cargo cargo) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Cargo.TABLE + " VALUES (?,?);");
			Long id = GeradorIdService.getInstance().nextId();
			insert.setLong(1, id);
			insert.setString(2, cargo.getNome());
			insert.execute();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar cargo.", e);
		}
	}
	
	@Override
	public void remove(Cargo cargo) {
		PreparedStatement remove = null;
		try {
			remove = this.connection.prepareStatement("DELETE FROM " + Cargo.TABLE + " WHERE " + Cargo.COL_ID + " = ?;");
			remove.setLong(1, cargo.getId());
			remove.execute();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover cargo " + cargo.getNome(), e);
		}
	}
	
	@Override
	public List<Cargo> findAll() {
		PreparedStatement query = null;
		List<Cargo> cargos = new ArrayList<Cargo>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Cargo.TABLE + ";");
			ResultSet resultado = query.executeQuery();
			cargos = this.cargoFactory.criarCargos(resultado);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar cargos.", e);
		}

		return cargos;
	}
	
	@Override
	public Cargo findById(Long id) {
		PreparedStatement query = null;
		Cargo cargo = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Cargo.TABLE + 
															 " WHERE " + Cargo.COL_ID + " = ?;");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				cargo = this.cargoFactory.criarCargo(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar cargo.", e);
		}

		return cargo;
	}
	
	@Override
	public Cargo update(Cargo cargo) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Cargo.TABLE + " SET " + Cargo.COL_NOME + " = ?" + 
																				"WHERE " + Cargo.COL_ID + " = ?;");
			update.setString(1, cargo.getNome());
			update.setLong(2, cargo.getId());
			update.execute();
			return this.findById(cargo.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar cargo " + cargo.getNome(), e);
		}
	}


}
