package br.com.wgengenharia.manager.coffe.tests;

import org.junit.Test;

import br.com.wgengenharia.manager.coffe.dao.ProductDAO;
import br.com.wgengenharia.manager.coffe.dao.ProductDAOImpl;
import br.com.wgengenharia.manager.coffe.model.Category;
import br.com.wgengenharia.manager.coffe.model.CategoryInterface;
import br.com.wgengenharia.manager.coffe.model.Product;

public class TestProductDAO {

	
	@Test
	public void insert() {
		Product product = new Product();
		CategoryInterface category = new Category();

		product.setBarcode(2313123);
		product.setCategory(category);
		product.setCost(23.8);
		product.setDescription("Inserindo produto por Teste");
		product.setId_product(0);
		product.setName("Produto Teste");
		product.setPrice(10.0);
		
		ProductDAO dao = new ProductDAOImpl();
		dao.insert(product);
		
	}

	public void update(Product entity) {
		
	}

	public void delete(Product entity) {
		
	}

	public Product findById(Integer id) {
		return null;
	}

	
	
	
}
