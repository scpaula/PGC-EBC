package br.com.fatec.projetos.api.service;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

import java.util.List;


import br.com.fatec.projetos.api.entity.Produto;

public interface ProdutoDAO {

	public Produto save(Produto produto);

	public Produto update(Produto produto);


	public void remove(Produto produto);

	public List<Produto> findAll();

	public Produto findById(Long id);
}
