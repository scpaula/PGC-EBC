package br.com.fatec.projetos.api.entity;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class Cargo {

	public static final String TABLE = "FATEC_CARGO";
	public static final String COL_ID = "COD";
	public static final String COL_NOME = "NOME";
	
	private Long id;
	private String nome;
	
	/**
	 * @return id
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 * @param id
	 */
	public void setId(Long id){
		this.id = id;
	}
	
	/**
	 * @return nome
	 */
	public String getNome(){
		return nome;
	}
	
	/**
	 * @param nome
	 */
	public void setNome(String nome){
		this.nome = nome;
	}
}
