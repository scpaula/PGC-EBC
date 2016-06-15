package br.com.fatec.projetos.api.service;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

import java.util.List;

import br.com.fatec.projetos.api.entity.Cargo;



public interface CargoDAO {

	public Cargo save(Cargo cargo);

	public Cargo update(Cargo cargo);


	public void remove(Cargo cargo);

	public List<Cargo> findAll();

	public Cargo findById(Long id);
	
}
