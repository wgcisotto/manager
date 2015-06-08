package br.com.wgengenharia.manager.coffe.tests;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.wgengenharia.manager.coffe.dao.CardDAO;
import br.com.wgengenharia.manager.coffe.dao.CardDAOImpl;
import br.com.wgengenharia.manager.coffe.dao.CategoryDAO;
import br.com.wgengenharia.manager.coffe.dao.CategoryDAOImpl;
import br.com.wgengenharia.manager.coffe.dao.ProductDAO;
import br.com.wgengenharia.manager.coffe.dao.ProductDAOImpl;
import br.com.wgengenharia.manager.coffe.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.coffe.model.Card;
import br.com.wgengenharia.manager.coffe.model.Category;
import br.com.wgengenharia.manager.coffe.model.Product;

public class TestRelacionamento {

	@Test
	public void testRelacionamento() {
		

    try {
    	
    	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
    	
    	CardDAO cardDAO = new CardDAOImpl(em);
    	ProductDAO prodDAO = new ProductDAOImpl(em);
    	CategoryDAO catgDAO = new CategoryDAOImpl(em);
    	
    	
    	Card card = new Card();
    	
    	Category categ = new Category();
    	categ.setDescription("categoria de teste");
    	categ.setName("Categ Teste");
    	catgDAO.insert(categ);
    	
    	Product a = new Product();
    	a.setName("a");
    	a.setCategory(categ);
    	a.setPrice(1.0);
    	a.setCost(1.0);
    	prodDAO.insert(a);
    	
    	Product b = new Product();
    	b.setName("b");
    	b.setCategory(categ);
    	b.setPrice(1.0);
    	b.setCost(1.0);
    	prodDAO.insert(b);
    	
    	card.setTotal(10.0);
    	card.addProduct(a);
    	card.addProduct(b);
    	
    	
    	cardDAO.insert(card);
       
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    finally{
    }
	}
}
	
	
