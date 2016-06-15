package br.com.fatec.projetos.web.action;

import javax.servlet.http.HttpServletRequest;

import br.com.fatec.projetos.api.entity.Cliente;
import br.com.fatec.projetos.api.service.ClienteDAO;
import br.com.fatec.projetos.core.impl.ClienteDAOImpl;

import com.opensymphony.xwork2.ActionSupport;

public class deletarAction extends ActionSupport {
	
	
	public String deletar(HttpServletRequest req){
		ClienteDAO clienteDAO = new ClienteDAOImpl();
		
		Long id = Long.parseLong(req.getParameter("id"));
		
		Cliente clienteSaved = clienteDAO.findById(id);
		
		clienteDAO.remove(clienteSaved);
		return SUCCESS;
	}
}
