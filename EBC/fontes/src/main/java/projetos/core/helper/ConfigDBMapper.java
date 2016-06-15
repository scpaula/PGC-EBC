package br.com.fatec.projetos.core.helper;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import liquibase.util.StringUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class ConfigDBMapper {

	private static ConfigDBMapper instance;
	private static JSONParser parser = new JSONParser();
	private static final  ClassLoader loader = ConfigDBMapper.class.getClassLoader();
	private static Map<String, Connection> connections = new HashMap<String, Connection>();
	private String defaultConnectionName;
	private List<String> possibleConfigs;
	
	 private ConfigDBMapper() {
		 loadConnections();
	}
	
	public static ConfigDBMapper getInstance() {
		if(instance == null){
			instance = new ConfigDBMapper();
		}
		return instance;
	}
	
	public void loadConnections(){
		try{
			String path = loader.getResource("br/com/fatec/projetos/core/config/databases.json").getPath();
		
			JSONArray configs = (JSONArray) ConfigDBMapper.parser.parse(new FileReader(path));
			if(configs.size() < 1){
				throw new Exception("É necessário ao menos uma configuração de Banco de Dados");
			
			}
			for(Object config : configs){
				JSONObject configJSON = (JSONObject) config;
			
				String configNameJSON = (String) configJSON.get("name");
				String url = (String) configJSON.get("url");
				String login = (String) configJSON.get("login");
				String password = (String) configJSON.get("password");
				String driverClassName = (String) configJSON.get("driverClassName");
			
				Class.forName(driverClassName);
			
				connections.put(configNameJSON, DriverManager.getConnection(url, login, password));
			}
			this.possibleConfigs = new ArrayList<String>(connections.keySet());
		}catch(Exception e){
		throw new RuntimeException(e);
		}
	}
	
	/*
	 * @param config
	 * */
	public void setDefaultConnectionName(String config){
		if(this.defaultConnectionName == null && (config != null )){
			if(this.possibleConfigs.contains(config)){
				this.defaultConnectionName = config;
			}else{
				throw new RuntimeException("Não exixste configuração com nome '" + config + "'.");
			}
		
		}	
	}
	
	/*
	 * @reurn {@link Connection} gerada a partir da propriedade 
	 * defaultConnectionName, ou null caso não esteja configurada
	 */
	public Connection getDefaultConnection(){
		if(this.defaultConnectionName == null){
			return null;
		}
		return this.getConnectionByConfig(this.defaultConnectionName);
	}
	
	/*@retun lista com todos os nomes de configurações disponíveis, essa lista
	 * é criada a partir do método 'loadConnections'
	 */
	public List<String> getPossibleConfigs(){
		return this.possibleConfigs;
	}
	
	/*@param configName
	 * @return {@link Connection} gerada a partir do arquivo de configuração que
	 * possua a 'configName' passada ou exception caso essa configuração 
	 * não existisse no momento de load das configs
	 */
	public Connection getConnectionByConfig(String configName){
		if(connections.containsKey(configName)){
			return connections.get(configName);
		}
		throw new RuntimeException("Não existe configuração com nome '" + configName + "'.");
	}

	
}

