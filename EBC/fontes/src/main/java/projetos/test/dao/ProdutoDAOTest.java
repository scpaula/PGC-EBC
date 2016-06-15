package br.com.fatec.projetos.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.projetos.api.entity.Produto;
import br.com.fatec.projetos.api.service.ProdutoDAO;
import br.com.fatec.projetos.core.helper.ProdutoFactory;
import br.com.fatec.projetos.core.impl.ProdutoDAOImpl;
import br.com.fatec.projetos.test.commons.ConfigDBTestCase;
import br.com.fatec.projetos.test.commons.CustomAsserts;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class ProdutoDAOTest extends ConfigDBTestCase{

	private ProdutoDAO produtoDAO;
	private ProdutoFactory produtoFactory;
	
	@Before
	public void config() {
		this.produtoDAO = new ProdutoDAOImpl();
		this.produtoFactory = new ProdutoFactory();
	}

	@Test
	public void saveProdutoTest() {
		Produto produtoToSave = this.produtoFactory.criarProduto(null, "Camisa", "Lacoste", 150.0, 10);
		Produto savedProduto = this.produtoDAO.save(produtoToSave);
		assertProduto(produtoToSave, savedProduto);
	}
	
	@Test
	public void findAllTest() {
		Produto produtoToSave1 = this.produtoFactory.criarProduto(null, "Camisa", "Lacoste", 150.0, 10);
		Produto produtoToSave2 = this.produtoFactory.criarProduto(null, "Camisa", "WindBeach", 150.0, 10);

		List<Produto> experados = new ArrayList<Produto>();

		experados.add(this.produtoDAO.save(produtoToSave1));
		experados.add(this.produtoDAO.save(produtoToSave2));

		List<Produto> produtos = this.produtoDAO.findAll();
		Assert.assertEquals(2, produtos.size());
		assertProdutos(experados, produtos);
	}

	@Test
	public void removeProdutoTest() {
	
		Produto produtoToSave = this.produtoFactory.criarProduto(null, "Camisa", "Lacoste", 150.0, 10);
		Produto savedProduto = this.produtoDAO.save(produtoToSave);

		assertProduto(produtoToSave, savedProduto);
		this.produtoDAO.remove(savedProduto);

		Assert.assertNull(this.produtoDAO.findById(savedProduto.getId()));
	}

	@Test
	public void updateProdutoTest() {

		Produto produtoToSave = this.produtoFactory.criarProduto(null, "Camisa", "Lacoste", 180.0, 10);
		Produto savedProduto = this.produtoDAO.save(produtoToSave);

		savedProduto.setMarca("WindBeach");

		Produto updatedProduto = this.produtoDAO.update(savedProduto);
		assertProduto(savedProduto, updatedProduto);
	}

	public static void assertProduto(Produto expected, Produto actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
		Assert.assertEquals(expected.getMarca(), actual.getMarca());
		CustomAsserts.assertDouble(expected.getPreco(), actual.getPreco());
		Assert.assertEquals(expected.getQuantidade(), actual.getQuantidade());
	}
	
	public static void assertProdutos(List<Produto> expected, List<Produto> actual) {
		for (int i = 0; i < expected.size(); i++) {
			assertProduto(expected.get(i), actual.get(i));
		}
	}

}
