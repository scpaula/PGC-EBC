package br.com.fatec.projetos.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projetos.api.entity.Cliente;
import br.com.fatec.projetos.api.service.ClienteDAO;
import br.com.fatec.projetos.core.helper.ClienteFactory;
import br.com.fatec.projetos.core.helper.ConfigDBMapper;
import br.com.fatec.projetos.core.impl.ClienteDAOImpl;
import br.com.fatec.projetos.test.commons.ConfigDBTestCase;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */


public class ClienteDAOTest extends ConfigDBTestCase{
	private ClienteDAO clienteDAO;
	private ClienteFactory clienteFactory;
	
	@Before
	public void config() {
		this.clienteDAO = new ClienteDAOImpl();
		this.clienteFactory = new ClienteFactory();
	}

	@Test
	public void saveClienteTest() { 
		Cliente clienteToSave = this.clienteFactory.criarCliente(null, "Maria", "de Souza", "mariasouza@gmail.com", "463.529.758-62", "(12) 3897-5666", "Rua das Orquídeas, 134", "Jardim Roseira", "Londrina", "PR", "maria123");
		Cliente savedCliente = this.clienteDAO.save(clienteToSave);
		assertCliente(clienteToSave, savedCliente);
	}
	
	@Test
	public void findAllTest() {
		Cliente clienteToSave1 = this.clienteFactory.criarCliente(null, "Maria", "de Souza", "mariasouza@gmail.com", "463.529.758-62", "(12) 3897-5666", "Rua das Orquídeas, 134", "Jardim Roseira", "Londrina", "Parana", "maria123");
		Cliente clienteToSave2 = this.clienteFactory.criarCliente(null, "Pedro", "dos Santos", "pedro.15@hotmail.com", "423.123.456-82", "(12) 3897-6891", "Rua Pedro Álvares Cabral, 455", "São Judas Tadeu", "Diamantina", "MG", "pedro6457");

		List<Cliente> experados = new ArrayList<Cliente>();

		experados.add(this.clienteDAO.save(clienteToSave1));
		experados.add(this.clienteDAO.save(clienteToSave2));

		List<Cliente> clientes = this.clienteDAO.findAll();
		Assert.assertEquals(2, clientes.size());
		assertClientes(experados, clientes);
	}

	@Test
	public void removeClienteTest() {
	
		Cliente clienteToSave = this.clienteFactory.criarCliente(null, "Maria", "de Souza", "mariasouza@gmail.com", "463.529.758-62", "(12) 3897-5666", "Rua das Orquídeas, 134", "Jardim Roseira", "Londrina", "PR", "maria123");
		Cliente savedCliente = this.clienteDAO.save(clienteToSave);

		assertCliente(clienteToSave, savedCliente);
		this.clienteDAO.remove(savedCliente);

		Assert.assertNull(this.clienteDAO.findById(savedCliente.getId()));
	}

	@Test
	public void updateClienteTest() {

		Cliente clienteToSave = this.clienteFactory.criarCliente(null, "Maria", "de Souza", "mariasouza@gmail.com", "463.529.758-62", "(12) 3897-5666", "Rua das Orquídeas, 134", "Jardim Roseira", "Londrina", "PR", "maria123");
		Cliente savedCliente = this.clienteDAO.save(clienteToSave);

		savedCliente.setNome("Maria");
		savedCliente.setTel("(11) 3698-5623");

		Cliente updatedCliente = this.clienteDAO.update(savedCliente);
		assertCliente(savedCliente, updatedCliente);
	}

	public static void assertCliente(Cliente expected, Cliente actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
		Assert.assertEquals(expected.getCPF(), actual.getCPF());
		Assert.assertEquals(expected.getTel(), actual.getTel());
	}
	
	public static void assertClientes(List<Cliente> expected, List<Cliente> actual) {
		for (int i = 0; i < expected.size(); i++) {
			assertCliente(expected.get(i), actual.get(i));
		}
	}
}
