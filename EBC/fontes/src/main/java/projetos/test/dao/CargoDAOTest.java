package br.com.fatec.projetos.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projetos.api.entity.Cargo;
import br.com.fatec.projetos.api.service.CargoDAO;
import br.com.fatec.projetos.core.helper.CargoFactory;
import br.com.fatec.projetos.core.impl.CargoDAOImpl;
import br.com.fatec.projetos.test.commons.ConfigDBTestCase;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class CargoDAOTest extends ConfigDBTestCase{

	private CargoDAO cargoDAO;
	private CargoFactory cargoFactory;
	
	@Before
	public void config() {
		this.cargoDAO = new CargoDAOImpl();
		this.cargoFactory = new CargoFactory();
	}

	@Test
	public void saveCargoTest() {
		Cargo cargoToSave = this.cargoFactory.criarCargo(null, "Vendedor");
		Cargo savedCargo = this.cargoDAO.save(cargoToSave);
		assertCargo(cargoToSave, savedCargo);
	}
	
	@Test
	public void findAllTest() {
		Cargo cargoToSave1 = this.cargoFactory.criarCargo(null, "Vendedor");
		Cargo cargoToSave2 = this.cargoFactory.criarCargo(null, "Estoquista");

		List<Cargo> experados = new ArrayList<Cargo>();

		experados.add(this.cargoDAO.save(cargoToSave1));
		experados.add(this.cargoDAO.save(cargoToSave2));

		List<Cargo> cargos = this.cargoDAO.findAll();
		Assert.assertEquals(2, cargos.size());
		assertCargos(experados, cargos);
	}

	@Test
	public void removeCargoTest() {
		Cargo cargoToSave = this.cargoFactory.criarCargo(null, "Vendedor");
		Cargo savedCargo = this.cargoDAO.save(cargoToSave);

		assertCargo(cargoToSave, savedCargo);
		this.cargoDAO.remove(savedCargo);

		Assert.assertNull(this.cargoDAO.findById(savedCargo.getId()));
	}

	@Test
	public void updateCargoTest() {

		Cargo cargoToSave = this.cargoFactory.criarCargo(null, "Vendedor");
		Cargo savedCargo = this.cargoDAO.save(cargoToSave);

		savedCargo.setNome("Gerente");

		Cargo updatedCargo = this.cargoDAO.update(savedCargo);
		assertCargo(savedCargo, updatedCargo);
	}

	public static void assertCargo(Cargo expected, Cargo actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
	}
	
	public static void assertCargos(List<Cargo> expected, List<Cargo> actual) {
		for (int i = 0; i < expected.size(); i++) {
			assertCargo(expected.get(i), actual.get(i));
		}
	}

}
