package br.com.fatec.projetos.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projetos.api.entity.Cargo;
import br.com.fatec.projetos.api.entity.Funcionario;
import br.com.fatec.projetos.api.service.CargoDAO;
import br.com.fatec.projetos.api.service.FuncionarioDAO;
import br.com.fatec.projetos.core.helper.CargoFactory;
import br.com.fatec.projetos.core.helper.FuncionarioFactory;
import br.com.fatec.projetos.core.impl.CargoDAOImpl;
import br.com.fatec.projetos.core.impl.FuncionarioDAOImpl;
import br.com.fatec.projetos.test.commons.ConfigDBTestCase;
import br.com.fatec.projetos.test.commons.CustomAsserts;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class FuncionarioDAOTest extends ConfigDBTestCase{

	private FuncionarioDAO funcionarioDAO;
	private FuncionarioFactory funcionarioFactory;
	CargoFactory cargoFactory;
	CargoDAO cargoDAO;
	
	@Before
	public void config() {
		this.funcionarioDAO = new FuncionarioDAOImpl();
		this.funcionarioFactory = new FuncionarioFactory();
		this.cargoFactory = new CargoFactory();
		this.cargoDAO = new CargoDAOImpl();
	}

	@Test
	public void saveFuncionarioTest() { 
		Cargo cargoTosave = cargoFactory.criarCargo(null, "Vendedor");
		Cargo savedCargo = cargoDAO.save(cargoTosave);
		
		Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "João", "425.369.258-42", "(12) 3887-5696", savedCargo);
		Funcionario savedFuncionario = this.funcionarioDAO.save(funcionarioToSave);
		assertFuncionario(funcionarioToSave, savedFuncionario);
	}
	
	@Test
	public void findAllTest() {
		Cargo cargoTosave = cargoFactory.criarCargo(null, "Vendedor");
		Cargo savedCargo = cargoDAO.save(cargoTosave);
		
		Funcionario funcionarioToSave1 = this.funcionarioFactory.criarFuncionario(null, "João", "425.369.258-42", "(12) 3887-5696", savedCargo);
		Funcionario funcionarioToSave2 = this.funcionarioFactory.criarFuncionario(null, "Maria", "444.333.218-22", "(12) 3257-5796", savedCargo);

		List<Funcionario> experados = new ArrayList<Funcionario>();

		experados.add(this.funcionarioDAO.save(funcionarioToSave1));
		experados.add(this.funcionarioDAO.save(funcionarioToSave2));

		List<Funcionario> funcionarios = this.funcionarioDAO.findAll();
		Assert.assertEquals(2, funcionarios.size());
		assertFuncionarios(experados, funcionarios);
	}

	@Test
	public void removeFuncionarioTest() {
		Cargo cargoTosave = cargoFactory.criarCargo(null, "Vendedor");
		Cargo savedCargo = cargoDAO.save(cargoTosave);

		Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "João", "425.369.258-42", "(12) 3887-5696", savedCargo);
		Funcionario savedFuncionario = this.funcionarioDAO.save(funcionarioToSave);

		assertFuncionario(funcionarioToSave, savedFuncionario);
		this.funcionarioDAO.remove(savedFuncionario);

		Assert.assertNull(this.funcionarioDAO.findById(savedFuncionario.getId()));
	}

	@Test
	public void updateFuncionarioTest() {
		Cargo cargoTosave = cargoFactory.criarCargo(null, "Vendedor");
		Cargo savedCargo = cargoDAO.save(cargoTosave);

		Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "João", "425.369.258-42", "(12) 3887-5696", savedCargo);
		Funcionario savedFuncionario = this.funcionarioDAO.save(funcionarioToSave);
		
		savedFuncionario.setNome("João");
		savedFuncionario.setCPF("485.349.958-78");
		savedFuncionario.setTel("(11) 3698-5623");

		Funcionario updatedFuncionario = this.funcionarioDAO.update(savedFuncionario);
		assertFuncionario(savedFuncionario, updatedFuncionario);
	}

	public static void assertFuncionario(Funcionario expected, Funcionario actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
		Assert.assertEquals(expected.getCPF(), actual.getCPF());
		Assert.assertEquals(expected.getTel(), actual.getTel());
		CustomAsserts.assertCargo(expected.getCargo(), actual.getCargo());
	}
	
	public static void assertFuncionarios(List<Funcionario> expected, List<Funcionario> actual) {
		for (int i = 0; i < expected.size(); i++) {
			assertFuncionario(expected.get(i), actual.get(i));
		}
	}
}
