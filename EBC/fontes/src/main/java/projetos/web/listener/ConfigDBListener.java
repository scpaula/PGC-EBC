package br.com.fatec.projetos.web.listener;

import java.sql.Connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import br.com.fatec.projetos.core.helper.ConfigDBMapper;

public class ConfigDBListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent arg0){
		ConfigDBMapper.getInstance().setDefaultConnectionName("fatec-prod");
		
		Connection conn  = ConfigDBMapper.getInstance().getDefaultConnection();
		
		Database database;
		try {
			database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
			Liquibase liquibase = new Liquibase("br/com/fatec/projetos/liquibase/changelog-master.xml",
					new ClassLoaderResourceAccessor(), database);
			liquibase.forceReleaseLocks();
			liquibase.update("fatec");
		} catch (LiquibaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
