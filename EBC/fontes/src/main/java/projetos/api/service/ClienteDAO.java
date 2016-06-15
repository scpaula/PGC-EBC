package br.com.fatec.projetos.api.service;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

import java.util.List;

import br.com.fatec.projetos.api.entity.Cliente;



public interface ClienteDAO {

	public Cliente save(Cliente cliente);

	public Cliente update(Cliente cliente);


	public void remove(Cliente cliente);

	public List<Cliente> findAll();

	public Cliente findById(Long id);
	
}
