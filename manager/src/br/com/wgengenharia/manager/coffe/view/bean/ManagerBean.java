package br.com.wgengenharia.manager.coffe.view.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.coffe.business.ProductBO;
import br.com.wgengenharia.manager.coffe.dao.CardDAO;
import br.com.wgengenharia.manager.coffe.dao.CardDAOImpl;
import br.com.wgengenharia.manager.coffe.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.coffe.model.Card;
import br.com.wgengenharia.manager.coffe.model.Category;
import br.com.wgengenharia.manager.coffe.model.Product;

@ManagedBean(name="manager")
@RequestScoped
public class ManagerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ProductBO productBO;
	
	private String teste;
	
	public ManagerBean() {
		//new no BO
		try {
			EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
    	
    	CardDAO cardDAO = new CardDAOImpl(em);
    	
    	Card card = new Card();
    	
    	Category categ = new Category();
    	categ.setDescription("categoria de teste");
    	categ.setName("Categ Teste");
    	
    	Product a = new Product();
    	a.setName("a");
    	a.setCategory(categ);
    	a.setPrice(1.0);
    	a.setCost(1.0);
    	
    	Product b = new Product();
    	b.setName("b");
    	b.setCategory(categ);
    	b.setPrice(1.0);
    	b.setCost(1.0);
    
    	card.setTotal(10.0);
    	card.addProduct(a);
    	card.addProduct(b);
    	
    	
    	cardDAO.insert(card);
       
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
//		
//		try {
//			
//			Connection db = DBManager.getInstace().getConnection();
//			
//			String selectSQL = "SELECT * FROM customersteste";
//			PreparedStatement preparedStatement = db.prepareStatement(selectSQL);
//			ResultSet rs = preparedStatement.executeQuery(selectSQL );
//			
//			while (rs.next()) {
//				String userid = rs.getString("customer_id");
//				String username = rs.getString("customer_name");	
//				System.out.println(userid);
//				System.out.println(username);
//			}
//			
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
		teste = "Testeeeeeeeeeeee";
	}
	
	public void setTeste(String manager) {
		this.teste = manager;
	}
	public String getTeste() {
		return teste;
	}
	
	public void executar(String algo){
		System.out.println(algo);
	}
	


}
