package br.com.wgengenharia.manager.coffe.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.primefaces.event.RowEditEvent;

import br.com.wgengenharia.manager.coffe.business.CardBO;
import br.com.wgengenharia.manager.coffe.business.CategoryBO;
import br.com.wgengenharia.manager.coffe.business.ClientBO;
import br.com.wgengenharia.manager.coffe.business.ProductBO;
import br.com.wgengenharia.manager.coffe.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.coffe.model.Card;
import br.com.wgengenharia.manager.coffe.model.Category;
import br.com.wgengenharia.manager.coffe.model.Client;
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
	
	private ClientBO clientBO;
	private Client newClient;
	private List<Client> clients;
	
	
	
	public ManagerBean() {
		//new no BO
		try {
			EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			// Estaciar as listas
			products = new ArrayList<>(); // Aqui deve carregar os produtos que estao no banco.
			cards = new ArrayList<>();  // Aqui deve carregar os cards  que estao no banco.
			categories = new ArrayList<>();  // Aqui deve carregar os cards  que estao no banco.
			clients = new ArrayList<>();
			
			
			productBO = new ProductBO(em);
			cardBO = new CardBO(em);
			categoryBO = new CategoryBO(em);
			clientBO = new ClientBO(em);
			
			newProduct = new Product();
			newCategory = new Category();
			newClient = new Client();
			
			selectedCategory = new Category();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Metodos Produto
	public void addProduct(){
		try {
			newProduct.setCategory(selectedCategory);
			productBO.insert(newProduct);
			products.add(newProduct);
			// limpa o product
			newProduct = new Product();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void updateProduct(RowEditEvent event){
		Product product = (Product) event.getObject();
		try {
			productBO.update(product);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
		FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucess!", "Produto Atualizado com sucesso"));
	}
	
	
	//Metodos Comanda
	public void addCard(){
		try {
			Card card = new Card();
			cardBO.insert(card);
			cards.add(card);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCard", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
//Metodos Categoria
	public void addCategory(){
		try {
			categoryBO.insert(newCategory);
			categories.add(newCategory);
			// limpa categoria
			newCategory = new Category();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void updateCategory(RowEditEvent event){
		Category category = (Category) event.getObject();
		try {
			categoryBO.update(category);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
		FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucess!", "Categoria Atualizada com sucesso"));
	}
	
	public void delCategory(Category category){
		try {
			categoryBO.delete(category);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
		FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucess!", "Categoria Excluida com sucesso"));
	}
	
	//Metodos Cliente
	public void addClient(){
		try {
//			newProduct.setCategory(selectedCategory);
			clientBO.insert(newClient);
			clients.add(newClient);
			// limpa o product
			newClient = new Client();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgClient", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void updateClient(RowEditEvent event){
		Client client = (Client) event.getObject();
		try {
			clientBO.update(client);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgClient", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",  e.getMessage() + " " + e.getCause()));
		}
		FacesContext.getCurrentInstance().addMessage("formManager:msgClient", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucess!", "Cliente Atualizado com sucesso"));
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
	// ## Comandas ##
	public List<Card> getCards() {
		return cards;
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
	//## Clientes ##
	public Client getNewClient() {
		return newClient;
	}
	public void setNewClient(Client newClient) {
		this.newClient = newClient;
	}
	public List<Client> getClients() {
		return clients;
	}
	
}
