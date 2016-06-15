package br.com.fatec.projetos.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projetos.api.entity.Cargo;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class CargoFactory {
	
	/**
	 * @param id
	 * @param nome
	 * @return o {@link Cargo} criado a partir dos parametros passados
	 */
	public Cargo criarCargo(Long id, String nome) {
			Cargo cargo = new Cargo();
			cargo.setId(id);
			cargo.setNome(nome);
			return cargo;
	}
	
	public  Cargo criarCargo(ResultSet resultado) {
		try {
			return this.criarCargo(resultado.getLong(Cargo.COL_ID), resultado.getString(Cargo.COL_NOME));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar cargo.", e);
		}
	}
	
	public List<Cargo> criarCargos(ResultSet resultado) {
		try {
			List<Cargo> cargos = new ArrayList<Cargo>();
			while (resultado.next()) {
				cargos.add(this.criarCargo(resultado));
			}
			return cargos;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar Cargo.", e);
		}
	}


}
