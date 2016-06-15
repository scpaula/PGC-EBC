package br.com.fatec.projetos.api.entity;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class Funcionario {

	public static final String TABLE = "FATEC_FUNCIONARIO";
	public static final String COL_ID = "COD";
	public static final String COL_NOME = "NOME";
	public static final String COL_CPF = "CPF";
	public static final String COL_TEL = "TEL";
	public static final String COL_CARGO = "ID_CARGO";
	
	private Long id;
	private String nome;
	private String CPF;
	private String tel;
	private Cargo cargo;
	
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
	
	/**
	 * @return CPF
	 */
	public String getCPF(){
		return CPF;
	}
	
	/**
	 * @param CPF
	 */
	public void setCPF(String CPF){
		this.CPF = CPF;
	}
	
	/**
	 * @return tel
	 */
	public String getTel(){
		return tel;
	}
	
	/**
	 * @param tel
	 */
	public void setTel(String tel){
		this.tel = tel;
	}
	
	/**
	 * @return Cargo
	 */
	public Cargo getCargo(){
		return cargo;
	}
	
	/**
	 * @param cargo
	 */
	public void setCargo(Cargo cargo){
		this.cargo = cargo;
	}
	
	
}
