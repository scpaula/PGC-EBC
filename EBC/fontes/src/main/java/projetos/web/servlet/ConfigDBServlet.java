package br.com.fatec.projetos.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import br.com.fatec.projetos.core.helper.ConfigDBMapper;

public class ConfigDBServlet extends HttpServlet{

	public void init() throws ServletException{
		//ConfigDBMapper.getInstance().setDefaultConnectionName("fatec-prod");
	}
}
