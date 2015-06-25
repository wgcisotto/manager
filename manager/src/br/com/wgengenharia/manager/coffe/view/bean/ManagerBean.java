package br.com.wgengenharia.manager.coffe.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.coffe.business.CardBO;
import br.com.wgengenharia.manager.coffe.business.CategoryBO;
import br.com.wgengenharia.manager.coffe.business.ProductBO;
import br.com.wgengenharia.manager.coffe.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.coffe.model.Card;
import br.com.wgengenharia.manager.coffe.model.Category;
import br.com.wgengenharia.manager.coffe.model.Product;

@ManagedBean(name="manager")
@SessionScoped
public class ManagerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ProductBO productBO;
	private Product newProduct;
	private List<Product> products;
	
	private CardBO cardBO;
	private List<Card> cards;
	
	private CategoryBO categoryBO;
	private Category newCategory;
	private Category selectedCategory;
	private List<Category> categories;
	
	
	
	public ManagerBean() {
		//new no BO
		try {
			EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			// Estaciar as listas
			products = new ArrayList<>(); // Aqui deve carregar os produtos que estao no banco.
			cards = new ArrayList<>();  // Aqui deve carregar os cards  que estao no banco.
			categories = new ArrayList<>();  // Aqui deve carregar os cards  que estao no banco.
			
			
			
			productBO = new ProductBO(em);
			cardBO = new CardBO(em);
			categoryBO = new CategoryBO(em);
			
			newProduct = new Product();
			newCategory = new Category();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Metodos Produto
	public void addProduct(){
		// inserir no banco **** Aviso
		productBO.insert(newProduct);
		products.add(newProduct);
		// limpa o product
		newProduct = new Product();
	}
	
	
	//Metodos Comanda
	public void createCard(){
		// inserir no banco **** Aviso
		cards.add(new Card());
	}
	
//Metodos Categoria
	public void addCategory(){
		// inserir no banco **** Aviso
		categories.add(newCategory);
		// limpa categoria
		newCategory = new Category();
	}
	
	
	
	
	//get and setters
	
	
	// ## produto ##
	public List<Product> getProducts() {
		return products;
	}
	public Product getNewProduct() {
		return newProduct;
	}
	public void setNewProduct(Product newProduct) {
		this.newProduct = newProduct;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	// ## Comandas ##
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	// ## Categorias ##
	public Category getNewCategory() {
		return newCategory;
	}
	public void setNewCategory(Category newCategory) {
		this.newCategory = newCategory;
	}
	public Category getSelectedCategory() {
		return selectedCategory;
	}
	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
