package br.com.fatec.projetos.api.entity;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class Cliente {

	public static final String TABLE = "FATEC_CLIENTE";
	public static final String COL_ID = "COD";
	public static final String COL_NOME = "NOME";
	public static final String COL_SOBRENOME = "SOBRENOME";
	public static final String COL_EMAIL = "EMAIL";
	public static final String COL_CPF = "CPF";
	public static final String COL_TEL = "TEL";
	public static final String COL_ENDERECO = "ENDERECO";
	public static final String COL_BAIRRO = "BAIRRO";
	public static final String COL_CIDADE = "CIDADE";
	public static final String COL_ESTADO = "ESTADO";
	public static final String COL_SENHA = "SENHA";
	
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private String CPF;
	private String tel;
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;
	private String senha;
	
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
	 * @return sobrenome
	 */
	public String getSobrenome() {
		return sobrenome;
	}


	/**
	 * @param sobrenome
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
