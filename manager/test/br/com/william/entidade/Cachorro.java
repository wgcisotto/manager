package br.com.william.entidade;

public class Cachorro extends Animal {

	
	private int tamanho;

	private String raca;
	
	
	
	public int getTamanho(){
		return this.tamanho;
	}
	
	public void setTamanho(int tamanho){
		this.tamanho = tamanho;
	}
	
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public String getRaca() {
		return raca;
	}
	
	
}
