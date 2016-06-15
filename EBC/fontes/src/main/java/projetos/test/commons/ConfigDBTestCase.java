package br.com.fatec.projetos.test.commons;

import java.sql.Connection;
import java.sql.DriverManager;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.junit.After;
import org.junit.Before;

import br.com.fatec.projetos.core.helper.ConfigDBMapper;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public abstract class ConfigDBTestCase {

	@Before
	public void setUp() throws Exception {
		
		ConfigDBMapper.getInstance().setDefaultConnectionName("fatec-test");
		Connection conn  = ConfigDBMapper.getInstance().getDefaultConnection();
		
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
		Liquibase liquibase = new Liquibase("br/com/fatec/projetos/liquibase/changelog-master.xml",
				new ClassLoaderResourceAccessor(), database);
		liquibase.forceReleaseLocks();
		liquibase.update("fatec");
		
	}

	@After
	public void setDown() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "SA", "");
		conn.prepareStatement("DROP SCHEMA PUBLIC CASCADE;").execute();
	}

	
}
