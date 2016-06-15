package br.com.fatec.projetos.api.service;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

import java.util.List;

import br.com.fatec.projetos.api.entity.Funcionario;

public interface FuncionarioDAO {
	public Funcionario save(Funcionario funcionario);

	public Funcionario update(Funcionario funcionario);


	public void remove(Funcionario funcionario);

	public List<Funcionario> findAll();

	public Funcionario findById(Long id);
}
