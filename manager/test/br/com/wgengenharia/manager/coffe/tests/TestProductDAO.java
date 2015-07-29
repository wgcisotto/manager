package br.com.wgengenharia.manager.coffe.tests;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.wgengenharia.manager.coffe.business.ProductBO;
import br.com.wgengenharia.manager.coffe.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.coffe.model.Product;

public class TestProductDAO {

	
	@Test
	public void insert() {
		Product product = new Product();
//		CategoryInterface category = new Category();
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		product.setBarcode((long) 2313123);
//		product.setCategory(category);
		product.setCost(23.8);
		product.setDescription("Inserindo produto por Teste");
		product.setId_product(0);
		product.setName("Produto Teste");
		product.setPrice(10.0);
		
		try {
			ProductBO pBO = new ProductBO(em);
			pBO.insert(product);
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

	public void update(Product entity) {
		
	}

	public void delete(Product entity) {
		
	}

	public Product findById(Integer id) {
		return null;
	}

	
	
	
}
