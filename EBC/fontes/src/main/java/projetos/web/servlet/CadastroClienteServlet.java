package br.com.fatec.projetos.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.projetos.api.entity.Cliente;
import br.com.fatec.projetos.api.service.ClienteDAO;
import br.com.fatec.projetos.core.helper.ClienteFactory;
import br.com.fatec.projetos.core.impl.ClienteDAOImpl;

public class CadastroClienteServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/*PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("Cadastrado com sucesso !");
		out.println("Nome:" + req.getParameter("nome")+ req.getParameter("sobrenome") + "/n");
		out.println("Email:" + req.getParameter("email") + "\n");
		out.println("DDD (" + req.getParameter("ddd") + ") \n");
		out.println("Telefone:" + req.getParameter("telefone") + "\n");
		out.println("Endereço:" + req.getParameter("endereco") + "\n"); 
		out.println("Cidade:" + req.getParameter("cidade") + "\n");
		out.println("Estado" + req.getParameter("estado") + "\n");
		out.println("Bairro" + req.getParameter("bairro") + "\n");
		out.println("</body>");
		out.println("</html>");*/
		

		ClienteDAO clienteDAO = new ClienteDAOImpl();
		ClienteFactory clienteFactory = new ClienteFactory();
		
		//Criar Cliente
		Cliente clienteToSave = clienteFactory.criarCliente(null,  req.getParameter("nome"), req.getParameter("sobrenome"), req.getParameter("email"), 
															req.getParameter("cpf"), req.getParameter("telefone"), req.getParameter("endereco"), 
															req.getParameter("bairro"),req.getParameter("cidade"), req.getParameter("estado"), 
															req.getParameter("senha"));
		//Salvar Cliente
		clienteDAO.save(clienteToSave);
		
		List<Cliente> listaEntidades = clienteDAO.findAll();
		
		/*PrintWriter out = resp.getWriter();
		for(Cliente cadastrados: listaEntidades){
			out.println("<html>");
			out.println("<body>");
			out.println("Nome:" + cadastrados.getNome());
			ut.println("Email:" + cadastrados.getEmail());
			out.println("</body>");
			out.println("</html>");
			
		}*/
		
		req.setAttribute("listaEntidades", listaEntidades);
		req.getRequestDispatcher("/listarEntidades.jsp").forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
