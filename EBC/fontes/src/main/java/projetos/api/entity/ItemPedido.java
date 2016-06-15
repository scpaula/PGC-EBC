package br.com.fatec.projetos.api.entity;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class ItemPedido {

	public static final String TABLE = "FATEC_ITEM_PEDIDO";
	public static final String COL_ID = "COD";
	public static final String COL_PRODUTO = "ID_PRODUTO";
	public static final String COL_PEDIDO = "ID_PEDIDO";
	public static final String COL_QUANTIDADE = "QUANTIDADE";
	public static final String COL_VALOR = "VALOR";
	
	private Long id;
	private Produto produto;
	private Pedido pedido;
	private int quantidade;
	private double valor;
	
	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return produto
	 */
	public Produto getProduto() {
		return produto;
	}
	
	/**
	 * @param produto
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	/**
	 * @return pedido
	 */
	public Pedido getPedido() {
		return pedido;
	}
	
	/**
	 * @param pedido
	 */
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	/**
	 * @return quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}
	
	/**
	 * @param quantidade
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	/**
	 * @return valor
	 */
	public double getValor() {
		return valor;
	}
	
	/**
	 * @param valor
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	
}
