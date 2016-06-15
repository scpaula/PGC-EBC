package br.com.fatec.projetos.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projetos.api.entity.Cargo;
import br.com.fatec.projetos.api.entity.Cliente;
import br.com.fatec.projetos.api.entity.Funcionario;
import br.com.fatec.projetos.api.entity.Pedido;
import br.com.fatec.projetos.api.service.CargoDAO;
import br.com.fatec.projetos.api.service.ClienteDAO;
import br.com.fatec.projetos.api.service.FuncionarioDAO;
import br.com.fatec.projetos.api.service.PedidoDAO;
import br.com.fatec.projetos.core.helper.CargoFactory;
import br.com.fatec.projetos.core.helper.ClienteFactory;
import br.com.fatec.projetos.core.helper.FuncionarioFactory;
import br.com.fatec.projetos.core.helper.PedidoFactory;
import br.com.fatec.projetos.core.impl.CargoDAOImpl;
import br.com.fatec.projetos.core.impl.ClienteDAOImpl;
import br.com.fatec.projetos.core.impl.FuncionarioDAOImpl;
import br.com.fatec.projetos.core.impl.PedidoDAOImpl;
import br.com.fatec.projetos.test.commons.ConfigDBTestCase;
import br.com.fatec.projetos.test.commons.CustomAsserts;

public class PedidoDAOTest extends ConfigDBTestCase{

		private PedidoDAO pedidoDAO;
		private PedidoFactory pedidoFactory;
		private ClienteDAO clienteDAO;
		private ClienteFactory clienteFactory;
		private FuncionarioFactory funcionarioFactory;
		private FuncionarioDAO funcionarioDAO;
		private CargoFactory cargoFactory;
		private CargoDAO cargoDAO;
		
		@Before
		public void config() {
			this.pedidoDAO = new PedidoDAOImpl();
			this.pedidoFactory = new PedidoFactory();
			this.clienteDAO = new ClienteDAOImpl();
			this.clienteFactory = new ClienteFactory();
			this.funcionarioDAO = new FuncionarioDAOImpl();
			this.funcionarioFactory = new FuncionarioFactory();
			this.cargoDAO = new CargoDAOImpl();
			this.cargoFactory = new CargoFactory();
		}

		@Test
		public void savePedidoTest() {
			Cliente clienteToSave = this.clienteFactory.criarCliente(null, "Maria", "de Souza", "mariasouza@gmail.com", "463.529.758-62", "(12) 3897-5666", "Rua das Orquídeas, 134", "Jardim Roseira", "Londrina", "Parana", "maria123");
			Cliente savedCliente = this.clienteDAO.save(clienteToSave);
			
			Cargo cargoTosave = cargoFactory.criarCargo(null, "Vendedor");
			Cargo savedCargo = cargoDAO.save(cargoTosave);
			
			Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "João", "425.369.258-42", "(12) 3887-5696", savedCargo);
			Funcionario savedFuncionario = this.funcionarioDAO.save(funcionarioToSave);
			
			Pedido pedidoToSave = this.pedidoFactory.criarPedido(null, savedCliente, savedFuncionario, 2, 300.0);
			Pedido savedPedido = this.pedidoDAO.save(pedidoToSave);
			assertPedido(pedidoToSave, savedPedido);
		}
		
		@Test
		public void findAllTest() {
			Cliente clienteToSave1 = this.clienteFactory.criarCliente(null, "Maria", "de Souza", "mariasouza@gmail.com", "463.529.758-62", "(12) 3897-5666", "Rua das Orquídeas, 134", "Jardim Roseira", "Londrina", "Parana", "maria123");
			Cliente savedCliente1 = this.clienteDAO.save(clienteToSave1);
			
			Cliente clienteToSave2 = this.clienteFactory.criarCliente(null, "Joaquim", "Pereira da Silva", "jo@gmail.com", "213.544.563-12", "(11) 2897-1456", "Rua José Epaminondas, 765", "Vila Industrial", "Duque de Caxias", "RS", "jo05031952");
			Cliente savedCliente2 = this.clienteDAO.save(clienteToSave2);
			
			Cargo cargoTosave1 = cargoFactory.criarCargo(null, "Vendedor");
			Cargo savedCargo1 = cargoDAO.save(cargoTosave1);
			
			Funcionario funcionarioToSave1 = this.funcionarioFactory.criarFuncionario(null, "João", "425.369.258-42", "(12) 3887-5696", savedCargo1);
			Funcionario savedFuncionario1 = this.funcionarioDAO.save(funcionarioToSave1);
			
			Cargo cargoTosave2 = cargoFactory.criarCargo(null, "Vendedor");
			Cargo savedCargo2 = cargoDAO.save(cargoTosave2);
			
			Funcionario funcionarioToSave2 = this.funcionarioFactory.criarFuncionario(null, "João", "425.369.258-42", "(12) 3887-5696", savedCargo2);
			Funcionario savedFuncionario2 = this.funcionarioDAO.save(funcionarioToSave2);
			
			Pedido pedidoToSave1 = this.pedidoFactory.criarPedido(null, savedCliente1, savedFuncionario1, 2, 0.0);
			Pedido pedidoToSave2 = this.pedidoFactory.criarPedido(null, savedCliente2, savedFuncionario2, 5, 0.0);

			List<Pedido> experados = new ArrayList<Pedido>();

			experados.add(this.pedidoDAO.save(pedidoToSave1));
			experados.add(this.pedidoDAO.save(pedidoToSave2));

			List<Pedido> pedidos = this.pedidoDAO.findAll();
			Assert.assertEquals(2, pedidos.size());
			assertPedidos(experados, pedidos);
		}

		@Test
		public void removePedidoTest() {
			Cliente clienteToSave = this.clienteFactory.criarCliente(null, "Maria", "de Souza", "mariasouza@gmail.com", "463.529.758-62", "(12) 3897-5666", "Rua das Orquídeas, 134", "Jardim Roseira", "Londrina", "Parana", "maria123");
			Cliente savedCliente = this.clienteDAO.save(clienteToSave);
			
			Cargo cargoTosave = cargoFactory.criarCargo(null, "Vendedor");
			Cargo savedCargo = cargoDAO.save(cargoTosave);
			
			Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "João", "425.369.258-42", "(12) 3887-5696", savedCargo);
			Funcionario savedFuncionario = this.funcionarioDAO.save(funcionarioToSave);
			
			Pedido pedidoToSave = this.pedidoFactory.criarPedido(null, savedCliente, savedFuncionario, 5, 300.0);
			Pedido savedPedido = this.pedidoDAO.save(pedidoToSave);

			assertPedido(pedidoToSave, savedPedido);
			this.pedidoDAO.remove(savedPedido);

			Assert.assertNull(this.pedidoDAO.findById(savedPedido.getId()));
		}

		@Test
		public void updatePedidoTest() {
			Cliente clienteToSave = this.clienteFactory.criarCliente(null, "Maria", "de Souza", "mariasouza@gmail.com", "463.529.758-62", "(12) 3897-5666", "Rua das Orquídeas, 134", "Jardim Roseira", "Londrina", "Parana", "maria123");
			Cliente savedCliente = this.clienteDAO.save(clienteToSave);
			
			Cargo cargoTosave = cargoFactory.criarCargo(null, "Vendedor");
			Cargo savedCargo = cargoDAO.save(cargoTosave);
			
			Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "João", "425.369.258-42", "(12) 3887-5696", savedCargo);
			Funcionario savedFuncionario = this.funcionarioDAO.save(funcionarioToSave);
			
			Pedido pedidoToSave = this.pedidoFactory.criarPedido(null, savedCliente, savedFuncionario, 5, 300.0);
			Pedido savedPedido = this.pedidoDAO.save(pedidoToSave);

			savedPedido.setQuantidade(10);
			savedPedido.setTotal(1.050);

			Pedido updatedPedido = this.pedidoDAO.update(savedPedido);
			assertPedido(savedPedido, updatedPedido);
		}

		public static void assertPedido(Pedido expected, Pedido actual) {
			CustomAsserts.assertCliente(expected.getCliente(), actual.getCliente());
	
		}
		
		public static void assertPedidos(List<Pedido> expected, List<Pedido> actual) {
			for (int i = 0; i < expected.size(); i++) {
				assertPedido(expected.get(i), actual.get(i));
			}
		}


}
