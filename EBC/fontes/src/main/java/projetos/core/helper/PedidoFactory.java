package br.com.fatec.projetos.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.projetos.api.entity.Cliente;
import br.com.fatec.projetos.api.entity.Funcionario;
import br.com.fatec.projetos.api.entity.Pedido;
import br.com.fatec.projetos.api.service.ClienteDAO;
import br.com.fatec.projetos.api.service.FuncionarioDAO;
import br.com.fatec.projetos.core.impl.ClienteDAOImpl;
import br.com.fatec.projetos.core.impl.FuncionarioDAOImpl;



/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class PedidoFactory {
	
	ClienteDAO clienteDAO = new ClienteDAOImpl();
	FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();
	
	/**
	 * @param id
	 * @param cliente
	 * @param funcionario
	 * @param quantidade
	 * @param total
	 * @return o {@link Pedido} criado a partir dos parametros passados
	 */
	
	public Pedido criarPedido(Long id, Cliente cliente, Funcionario funcionario, int quantidade, double total) {
		Pedido pedido = new Pedido();
		pedido.setId(id);
		pedido.setCliente(cliente);
		pedido.setFuncionario(funcionario);
		pedido.setQuantidade(quantidade);
		pedido.setTotal(total);
		return pedido;
	}
	
	public Pedido criarPedido(Long id, Long cliente, Long funcionario, int quantidade, double total) {
		Pedido pedido = new Pedido();
		pedido.setId(id);
		pedido.setCliente(clienteDAO.findById(cliente));
		pedido.setFuncionario(funcionarioDAO.findById(funcionario));
		return pedido;
	}

	public Pedido criarPedido(ResultSet resultado) {
		try {
			return this.criarPedido(resultado.getLong(Pedido.COL_ID), resultado.getLong(Pedido.COL_CLIENTE),
					resultado.getLong(Pedido.COL_FUNCIONARIO), resultado.getInt(Pedido.COL_QUANTIDADE), resultado.getDouble(Pedido.COL_TOTAL));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar Pedido.", e);
		}
	}
	
	public List<Pedido> criarPedidos(ResultSet resultado) {
		try {
			List<Pedido> pedidos = new ArrayList<Pedido>();
			while (resultado.next()) {
				pedidos.add(this.criarPedido(resultado));
			}
			return pedidos;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar Pedido.", e);
		}
	}

	
}
