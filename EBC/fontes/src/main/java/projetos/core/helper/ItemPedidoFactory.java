package br.com.fatec.projetos.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projetos.api.entity.ItemPedido;
import br.com.fatec.projetos.api.entity.Pedido;
import br.com.fatec.projetos.api.entity.Produto;
import br.com.fatec.projetos.api.service.PedidoDAO;
import br.com.fatec.projetos.api.service.ProdutoDAO;
import br.com.fatec.projetos.core.impl.PedidoDAOImpl;
import br.com.fatec.projetos.core.impl.ProdutoDAOImpl;


/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class ItemPedidoFactory {
	
	private ProdutoDAO produtoDAO = new ProdutoDAOImpl();
	private PedidoDAO pedidoDAO = new PedidoDAOImpl();
	
	/**
	 * @param id
	 * @param itemPedido
	 * @param quantidade
	 * @param valor
	 * @return o {@link ItemPedido} criado a partir dos parametros passados
	 */
	public ItemPedido criarItemPedido(Long id, Produto produto, Pedido pedido, int quantidade, double valor) {
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(id);
		itemPedido.setProduto(produto);
		itemPedido.setPedido(pedido);
		itemPedido.setQuantidade(quantidade);
		itemPedido.setValor(valor);
		return itemPedido;
	}
	
	public ItemPedido criarItemPedido(Long id, Long produto, Long pedido, int quantidade, double valor) {
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(id);
		itemPedido.setProduto(produtoDAO.findById(produto));
		itemPedido.setPedido(pedidoDAO.findById(pedido));
		itemPedido.setQuantidade(quantidade);
		itemPedido.setValor(valor);
		return itemPedido;
	}
	
	public ItemPedido criarItemPedido(ResultSet resultado) {
		try {
			return this.criarItemPedido(resultado.getLong(ItemPedido.COL_ID), resultado.getLong(ItemPedido.COL_PRODUTO),
					resultado.getLong(ItemPedido.COL_PEDIDO), resultado.getInt(ItemPedido.COL_QUANTIDADE), resultado.getDouble(ItemPedido.COL_VALOR));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar Item.", e);
		}
	}
	
	public List<ItemPedido> criarItemPedidos(ResultSet resultado) {
		try {
			List<ItemPedido> itemPedidos = new ArrayList<ItemPedido>();
			while (resultado.next()) {
				itemPedidos.add(this.criarItemPedido(resultado));
			}
			return itemPedidos;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar ItemPedido.", e);
		}
	}

	
}
