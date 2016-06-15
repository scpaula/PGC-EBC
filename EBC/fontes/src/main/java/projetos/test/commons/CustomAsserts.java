package br.com.fatec.projetos.test.commons;

import org.junit.Assert;

import br.com.fatec.projetos.api.entity.Cargo;
import br.com.fatec.projetos.api.entity.Cliente;
import br.com.fatec.projetos.api.entity.ItemPedido;
import br.com.fatec.projetos.api.entity.Produto;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class CustomAsserts {
	
	public static void assertDouble(double expected, double actual) {
		Double cexpected = expected;
		Double cactual = actual;
		Assert.assertEquals(cexpected, cactual);
	}
	
	public static void assertCargo(Cargo expected, Cargo actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
	}
	
	public static void assertProduto(Produto expected, Produto actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
		Assert.assertEquals(expected.getMarca(), actual.getMarca());
		CustomAsserts.assertDouble(expected.getPreco(), actual.getPreco());
		Assert.assertEquals(expected.getQuantidade(), actual.getQuantidade());
	}
	
	public static void assertCliente(Cliente expected, Cliente actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
		Assert.assertEquals(expected.getCPF(), actual.getCPF());
		Assert.assertEquals(expected.getTel(), actual.getTel());
	}
	
	public static void assertItemPedido(ItemPedido expected, ItemPedido actual) {
		CustomAsserts.assertProduto(expected.getProduto(), actual.getProduto());
		Assert.assertEquals(expected.getQuantidade(), actual.getQuantidade());
		CustomAsserts.assertDouble(expected.getValor(), actual.getValor());
	}
}
