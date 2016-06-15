package br.com.fatec.projetos.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projetos.api.entity.Cargo;
import br.com.fatec.projetos.api.entity.Funcionario;
import br.com.fatec.projetos.api.service.CargoDAO;
import br.com.fatec.projetos.core.impl.CargoDAOImpl;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class FuncionarioFactory {
	
	private CargoDAO cargoDAO = new CargoDAOImpl();

	/**
	 * @param id
	 * @param nome
	 * @param CPF
	 * @param tel
	 * @param cargo
	 * @return o {@link Funcionario} criado a partir dos parametros passados
	 */
	public Funcionario criarFuncionario(Long id, String nome, String CPF, String tel, Cargo cargo) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCPF(CPF);
		funcionario.setTel(tel);
		funcionario.setCargo(cargo);
		return funcionario;
	}
	
	public Funcionario criarFuncionario(Long id, String nome, String CPF, String tel, Long cargo) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCPF(CPF);
		funcionario.setTel(tel);
		funcionario.setCargo(cargoDAO.findById(cargo));
		return funcionario;
	}
	
	public Funcionario criarFuncionario(ResultSet resultado) {
		try {
			return this.criarFuncionario(resultado.getLong(Funcionario.COL_ID), resultado.getString(Funcionario.COL_NOME),
					resultado.getString(Funcionario.COL_CPF), resultado.getString(Funcionario.COL_TEL), resultado.getLong(Funcionario.COL_CARGO));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar Funcionario.", e);
		}
	}
	
	public List<Funcionario> criarFuncionarios(ResultSet resultado) {
		try {
			List<Funcionario> funcionarios = new ArrayList<Funcionario>();
			while (resultado.next()) {
				funcionarios.add(this.criarFuncionario(resultado));
			}
			return funcionarios;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar Funcionario.", e);
		}
	}
}
