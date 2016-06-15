package br.com.fatec.projetos.api.service;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

import java.util.List;

import br.com.fatec.projetos.api.entity.Pedido;

public interface PedidoDAO {

	public Pedido save(Pedido pedido);

	public Pedido update(Pedido pedido);


	public void remove(Pedido pedido);

	public List<Pedido> findAll();

	public Pedido findById(Long id);
}
