package br.com.fatec.projetos.api.service;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

import java.util.List;

import br.com.fatec.projetos.api.entity.ItemPedido;



public interface ItemPedidoDAO {
	
	public ItemPedido save(ItemPedido itemPedido);

	public ItemPedido update(ItemPedido itemPedido);


	public void remove(ItemPedido itemPedido);

	public List<ItemPedido> findAll();

	public ItemPedido findById(Long id);
}
