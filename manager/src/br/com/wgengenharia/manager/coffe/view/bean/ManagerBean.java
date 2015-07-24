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
import org.primefaces.event.SelectEvent;

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
	private Product selectedProduct;
	private List<Product> products;
	private Integer idCategProdct;
	
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
			cards = new ArrayList<>();  
			categories = new ArrayList<>();
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
			FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	//Metodos Produto
	public void addProduct(){
		try {
			Category categ = categoryBO.findById(idCategProdct);
			newProduct.setCategory(categ);
			idCategProdct = 0;
			productBO.insert(newProduct);
			products = productBO.listProducts();
			newProduct = new Product();
			// limpa o product
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
		FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Produto Inserido com sucesso"));
	}
  
	 public void onRowSelectProduct(SelectEvent event) {
		 selectedProduct = (Product) event.getObject();
	 }
	
	public void updateProduct(){
		try {
			if(selectedProduct!=null){
				productBO.update(selectedProduct);
				products = productBO.listProducts();
				selectedProduct = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Produto Atualizado com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar um Produto"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void delProduct(){
		try {
			if(selectedProduct!=null){
				productBO.delete(selectedProduct);
				products = productBO.listProducts();
				selectedProduct = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Produto Excluido com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar um Produto"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
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
		FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Categoria Atualizada com sucesso"));
	}
	
	public void delCategory(Category category){
		try {
			categoryBO.delete(category);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
		FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Categoria Excluida com sucesso"));
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
		FacesContext.getCurrentInstance().addMessage("formManager:msgClient", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Cliente Atualizado com sucesso"));
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
	public Product getSelectedProduct() {
		return selectedProduct;
	}
	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
	public Integer getIdCategProdct() {
		return idCategProdct;
	}
	public void setIdCategProdct(Integer idCategProdct) {
		this.idCategProdct = idCategProdct;
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
