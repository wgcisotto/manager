package br.com.william.teste;

import br.com.william.entidade.*;

public class Teste {

	
	public static void main(String[] args) {
		
		Cachorro dog = new Cachorro(); 
	
		dog.setRaca("Dogue Alemao");
		dog.setTamanho(100);
		dog.setNome("Xuxa");
		
		
		Animal cat = new Gato();
		
		cat.setNome("chato");
		
		
		System.out.println(dog.getRaca());
		System.out.println(dog.getTamanho());
		System.out.println(dog.getNome());
		
		
		
	}
	
	
	
}
