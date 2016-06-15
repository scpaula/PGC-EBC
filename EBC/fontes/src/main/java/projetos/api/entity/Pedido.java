package br.com.fatec.projetos.api.entity;

/**
 * @author Sonia
 *
 * @version 1.0.0
 */

public class Pedido {
	
	public static final String TABLE = "FATEC_PEDIDO";
	public static final String COL_ID = "COD";
	public static final String COL_CLIENTE = "ID_CLIENTE";
	public static final String COL_FUNCIONARIO = "ID_FUNCIONARIO";
	public static final String COL_ITEM = "ID_ITEM";
	public static final String COL_QUANTIDADE = "QUANTIDADE";
	public static final String COL_TOTAL = "TOTAL";
	
	private Long id;
	private Cliente cliente;
	private Funcionario funcionario;
	private int quantidade;
	private double total;
	
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
	 * @return Cliente
	 */
	public Cliente getCliente(){
		return cliente;
	}
	
	/**
	 * @param cliente
	 */
	public void setCliente(Cliente cliente){
		this.cliente = cliente;
	}
	
	/**
	 * @return Funcionario
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
		this.quantidade += quantidade;
	}
	
	/**
	 * @return total
	 */
	public double getTotal(){
		return total;
	}
	
	/**
	 * @param total
	 */
	public void setTotal(double total){
		this.total = total;
	}
	
	public void calcularTotal(ItemPedido item){
		double total = (item.getValor() + item.getQuantidade());
		setTotal(total);
	}
	
	public void calcularQuantidade(ItemPedido item){
		setQuantidade(item.getQuantidade());
	}
}
