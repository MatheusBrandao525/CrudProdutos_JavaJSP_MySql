package model;

public class Produto {
	private int codigo;
	private String nome;
	private String descricao;
	private double valorVenda;
	private String marca;
	private String modelo;
	private String anoFabricacao;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(double d) {
		this.valorVenda = d;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", valorVenda="
				+ valorVenda + ", marca=" + marca + ", modelo=" + modelo + ", anoFabricacao=" + anoFabricacao + "]";
	}
	
	
}
