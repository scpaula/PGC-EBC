package br.com.fatec.projetos.api.entity;


/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class Produto{
	
	public static final String TABLE = "FATEC_PRODUTO";
	public static final String COL_ID = "COD";
	public static final String COL_NOME = "NOME";
	public static final String COL_PRECO = "PRECO";
	public static final String COL_QUANTIDADE = "QUANTIDADE";
	public static final String COL_MARCA = "MARCA";
	
	private Long id;
	private String nome;
	private double preco;
	private int quantidade;
	private String marca;
	
	public Long getId(){
		return id;
	}
	
	/**
	 * @param id
	 */
	public void setId(Long id){
		this.id = id;
	}
	
	public String getNome(){
		return nome;
	}
	
	/**
	 * @param nome
	 */
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public double getPreco(){
		return preco;
	}
	
	/**
	 * @param preco
	 */
	public void setPreco(double preco){
		this.preco = preco;
	}
	
	public int getQuantidade(){
		return quantidade;
	}
	
	/**
	 * @param quantidade
	 */
	public void seQuantidade(int quantidade){
		this.quantidade = quantidade;
	}
	
	public String getMarca(){
		return marca;
	}
	
	/**
	 * @param marca
	 */
	public void setMarca(String marca){
		this.marca = marca;
	}
}
