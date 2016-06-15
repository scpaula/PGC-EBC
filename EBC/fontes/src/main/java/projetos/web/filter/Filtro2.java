package br.com.fatec.projetos.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Filtro2 implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
Object filtrosObjetc = req.getAttribute("filtros");
		
		List<String> filtros = null;
		if(filtrosObjetc != null){
			filtros = (ArrayList<String>) filtrosObjetc;
		}else{
			filtros = new ArrayList<String>();
		}
		filtros.add(this.getClass().getCanonicalName());
		req.setAttribute("filtros", filtros);
		
		chain.doFilter(req, resp);
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

