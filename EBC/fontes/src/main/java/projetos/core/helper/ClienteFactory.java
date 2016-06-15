package br.com.fatec.projetos.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projetos.api.entity.Cliente;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class ClienteFactory {

	/**
	 * @param id
	 * @param nome
	 * @param sobrenome
	 * @param CPF
	 * @param tel
	 * @param endereco
	 * @return o {@link Cliente} criado a partir dos parametros passados
	 */
	public Cliente criarCliente(Long id, String nome, String sobrenome, String email, String CPF, String tel, String endereco, String bairro, String cidade, String estado, String senha) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(nome);
		cliente.setSobrenome(sobrenome);
		cliente.setEmail(email);
		cliente.setCPF(CPF);
		cliente.setTel(tel);
		cliente.setEndereco(endereco);
		cliente.setBairro(bairro);
		cliente.setCidade(cidade);
		cliente.setEstado(estado);
		cliente.setSenha(senha);
		return cliente;
	}

	public Cliente criarCliente(ResultSet resultado) {
		try {
			return this.criarCliente(resultado.getLong(Cliente.COL_ID), resultado.getString(Cliente.COL_NOME),
									resultado.getString(Cliente.COL_SOBRENOME), resultado.getString(Cliente.COL_EMAIL), 
									resultado.getString(Cliente.COL_CPF), resultado.getString(Cliente.COL_TEL),
									resultado.getString(Cliente.COL_ENDERECO), resultado.getString(Cliente.COL_BAIRRO),
									resultado.getString(Cliente.COL_CIDADE), resultado.getString(Cliente.COL_ESTADO),
									resultado.getString(Cliente.COL_SENHA));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar cliente.", e);
		}
	}
	
	public List<Cliente> criarClientes(ResultSet resultado) {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			while (resultado.next()) {
				clientes.add(this.criarCliente(resultado));
			}
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar Cliente.", e);
		}
	}

	
}
