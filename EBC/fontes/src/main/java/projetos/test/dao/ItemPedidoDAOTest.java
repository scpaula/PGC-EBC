package br.com.fatec.projetos.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projetos.api.entity.Cargo;
import br.com.fatec.projetos.api.entity.Cliente;
import br.com.fatec.projetos.api.entity.Funcionario;
import br.com.fatec.projetos.api.entity.ItemPedido;
import br.com.fatec.projetos.api.entity.Pedido;
import br.com.fatec.projetos.api.entity.Produto;
import br.com.fatec.projetos.api.service.CargoDAO;
import br.com.fatec.projetos.api.service.ClienteDAO;
import br.com.fatec.projetos.api.service.FuncionarioDAO;
import br.com.fatec.projetos.api.service.ItemPedidoDAO;
import br.com.fatec.projetos.api.service.PedidoDAO;
import br.com.fatec.projetos.api.service.ProdutoDAO;
import br.com.fatec.projetos.core.helper.CargoFactory;
import br.com.fatec.projetos.core.helper.ClienteFactory;
import br.com.fatec.projetos.core.helper.FuncionarioFactory;
import br.com.fatec.projetos.core.helper.ItemPedidoFactory;
import br.com.fatec.projetos.core.helper.PedidoFactory;
import br.com.fatec.projetos.core.helper.ProdutoFactory;
import br.com.fatec.projetos.core.impl.CargoDAOImpl;
import br.com.fatec.projetos.core.impl.ClienteDAOImpl;
import br.com.fatec.projetos.core.impl.FuncionarioDAOImpl;
import br.com.fatec.projetos.core.impl.ItemPedidoDAOImpl;
import br.com.fatec.projetos.core.impl.PedidoDAOImpl;
import br.com.fatec.projetos.core.impl.ProdutoDAOImpl;
import br.com.fatec.projetos.test.commons.ConfigDBTestCase;
import br.com.fatec.projetos.test.commons.CustomAsserts;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class ItemPedidoDAOTest extends ConfigDBTestCase{

	private ItemPedidoDAO ItemDAO;
	private ItemPedidoFactory ItemFactory;
	private ProdutoDAO produtoDAO;
	private ProdutoFactory produtoFactory;
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
		this.ItemDAO = new ItemPedidoDAOImpl();
		this.ItemFactory = new ItemPedidoFactory();
		this.produtoDAO = new ProdutoDAOImpl();
		this.produtoFactory = new ProdutoFactory();
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
	public void saveItemPedidoTest() {
	
		Produto produtoToSave = produtoFactory.criarProduto(null, "Camisa", "Lacoste", 150.0, 10);
		Produto savedProduto = produtoDAO.save(produtoToSave);
		
		Cliente clienteToSave = this.clienteFactory.criarCliente(null, "Maria", "de Souza", "mariasouza@gmail.com", "463.529.758-62", "(12) 3897-5666", "Rua das Orquídeas, 134", "Jardim Roseira", "Londrina", "Parana", "maria123");
		Cliente savedCliente = this.clienteDAO.save(clienteToSave);
		
		Cargo cargoTosave = cargoFactory.criarCargo(null, "Vendedor");
		Cargo savedCargo = cargoDAO.save(cargoTosave);
		
		Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "João", "425.369.258-42", "(12) 3887-5696", savedCargo);
		Funcionario savedFuncionario = this.funcionarioDAO.save(funcionarioToSave);
		
		Pedido pedidoToSave = this.pedidoFactory.criarPedido(null, savedCliente, savedFuncionario, 0, 0.0);
		Pedido savedPedido = this.pedidoDAO.save(pedidoToSave);
		
		ItemPedido ItemToSave = this.ItemFactory.criarItemPedido(null, savedProduto, savedPedido, 2, 150.0);
		ItemPedido savedItemPedido = this.ItemDAO.save(ItemToSave);
		savedPedido.calcularTotal(ItemToSave);
		savedPedido.calcularQuantidade(ItemToSave);
		
		assertItemPedido(ItemToSave, savedItemPedido);
	}
	
	@Test
	public void findAllTest() {
		Produto produtoToSave1 = produtoFactory.criarProduto(null, "Camisa", "Lacoste", 150.0, 10);
		Produto savedProduto1 = produtoDAO.save(produtoToSave1);
		
		Produto produtoToSave2 = produtoFactory.criarProduto(null, "Camisa", "Lacoste", 150.0, 10);
		Produto savedProduto2 = produtoDAO.save(produtoToSave2);
		
		Cliente clienteToSave = this.clienteFactory.criarCliente(null, "Maria", "de Souza", "mariasouza@gmail.com", "463.529.758-62", "(12) 3897-5666", "Rua das Orquídeas, 134", "Jardim Roseira", "Londrina", "Parana", "maria123");
		Cliente savedCliente = this.clienteDAO.save(clienteToSave);
		
		Cargo cargoTosave = cargoFactory.criarCargo(null, "Vendedor");
		Cargo savedCargo = cargoDAO.save(cargoTosave);
		
		Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "João", "425.369.258-42", "(12) 3887-5696", savedCargo);
		Funcionario savedFuncionario = this.funcionarioDAO.save(funcionarioToSave);
		
		Pedido pedidoToSave = this.pedidoFactory.criarPedido(null, savedCliente, savedFuncionario, 0, 0.0);
		Pedido savedPedido = this.pedidoDAO.save(pedidoToSave);
		
		ItemPedido ItemToSave1 = this.ItemFactory.criarItemPedido(null, savedProduto1, savedPedido, 2, 150.0);
		savedPedido.calcularTotal(ItemToSave1);
		savedPedido.calcularQuantidade(ItemToSave1);
		
		ItemPedido ItemToSave2 = this.ItemFactory.criarItemPedido(null, savedProduto2, savedPedido, 4, 150.0);
		savedPedido.calcularTotal(ItemToSave2);
		savedPedido.calcularQuantidade(ItemToSave2);

		List<ItemPedido> experados = new ArrayList<ItemPedido>();

		experados.add(this.ItemDAO.save(ItemToSave1));
		experados.add(this.ItemDAO.save(ItemToSave2));

		List<ItemPedido> Items = this.ItemDAO.findAll();
		Assert.assertEquals(2, Items.size());
		assertItemPedidos(experados, Items);
	}

	@Test
	public void removeItemPedidoTest() {
		Produto produtoToSave = produtoFactory.criarProduto(null, "Camisa", "Lacoste", 150.0, 10);
		Produto savedProduto = produtoDAO.save(produtoToSave);
		
		Cliente clienteToSave = this.clienteFactory.criarCliente(null, "Maria", "de Souza", "mariasouza@gmail.com", "463.529.758-62", "(12) 3897-5666", "Rua das Orquídeas, 134", "Jardim Roseira", "Londrina", "Parana", "maria123");
		Cliente savedCliente = this.clienteDAO.save(clienteToSave);
		
		Cargo cargoTosave = cargoFactory.criarCargo(null, "Vendedor");
		Cargo savedCargo = cargoDAO.save(cargoTosave);
		
		Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "João", "425.369.258-42", "(12) 3887-5696", savedCargo);
		Funcionario savedFuncionario = this.funcionarioDAO.save(funcionarioToSave);
		
		Pedido pedidoToSave = this.pedidoFactory.criarPedido(null, savedCliente, savedFuncionario, 0, 0.0);
		Pedido savedPedido = this.pedidoDAO.save(pedidoToSave);
	
		ItemPedido ItemToSave = this.ItemFactory.criarItemPedido(null, savedProduto, savedPedido, 2, 150.0);
		ItemPedido savedItemPedido = this.ItemDAO.save(ItemToSave);
		savedPedido.calcularTotal(ItemToSave);
		savedPedido.calcularQuantidade(ItemToSave);

		assertItemPedido(ItemToSave, savedItemPedido);
		this.ItemDAO.remove(savedItemPedido);

		Assert.assertNull(this.ItemDAO.findById(savedItemPedido.getId()));
	}

	@Test
	public void updateItemPedidoTest() {
		Produto produtoToSave = produtoFactory.criarProduto(null, "Camisa", "Lacoste", 150.0, 10);
		Produto savedProduto = produtoDAO.save(produtoToSave);

		Cliente clienteToSave = this.clienteFactory.criarCliente(null, "Maria", "de Souza", "mariasouza@gmail.com", "463.529.758-62", "(12) 3897-5666", "Rua das Orquídeas, 134", "Jardim Roseira", "Londrina", "Parana", "maria123");
		Cliente savedCliente = this.clienteDAO.save(clienteToSave);
		
		Cargo cargoTosave = cargoFactory.criarCargo(null, "Vendedor");
		Cargo savedCargo = cargoDAO.save(cargoTosave);
		
		Funcionario funcionarioToSave = this.funcionarioFactory.criarFuncionario(null, "João", "425.369.258-42", "(12) 3887-5696", savedCargo);
		Funcionario savedFuncionario = this.funcionarioDAO.save(funcionarioToSave);
		
		Pedido pedidoToSave = this.pedidoFactory.criarPedido(null, savedCliente, savedFuncionario, 0, 0.0);
		Pedido savedPedido = this.pedidoDAO.save(pedidoToSave);
		
		ItemPedido ItemToSave = this.ItemFactory.criarItemPedido(null, savedProduto, savedPedido, 2, 150.0);
		ItemPedido savedItemPedido = this.ItemDAO.save(ItemToSave);
		savedPedido.calcularTotal(ItemToSave);
		savedPedido.calcularQuantidade(ItemToSave);

		savedItemPedido.setQuantidade(2);
		savedItemPedido.setValor(180.0);;

		ItemPedido updatedItemPedido = this.ItemDAO.update(savedItemPedido);
		assertItemPedido(savedItemPedido, updatedItemPedido);
	}

	public static void assertItemPedido(ItemPedido expected, ItemPedido actual) {
		CustomAsserts.assertProduto(expected.getProduto(), actual.getProduto());
		Assert.assertEquals(expected.getQuantidade(), actual.getQuantidade());
		CustomAsserts.assertDouble(expected.getValor(), actual.getValor());
	}
	
	public static void assertItemPedidos(List<ItemPedido> expected, List<ItemPedido> actual) {
		for (int i = 0; i < expected.size(); i++) {
			assertItemPedido(expected.get(i), actual.get(i));
		}
	}
}
