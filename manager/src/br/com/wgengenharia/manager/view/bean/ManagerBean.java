package br.com.wgengenharia.manager.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import br.com.wgengenharia.manager.business.BranchBO;
import br.com.wgengenharia.manager.business.CardBO;
import br.com.wgengenharia.manager.business.CategoryBO;
import br.com.wgengenharia.manager.business.ClientBO;
import br.com.wgengenharia.manager.business.CompanyBO;
import br.com.wgengenharia.manager.business.EmployeeBO;
import br.com.wgengenharia.manager.business.ModuleBO;
import br.com.wgengenharia.manager.business.ProductBO;
import br.com.wgengenharia.manager.business.SaleBO;
import br.com.wgengenharia.manager.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.facade.ManagerSaleFacadeInterface;
import br.com.wgengenharia.manager.factory.ManagerSaleFactory;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Card;
import br.com.wgengenharia.manager.model.Category;
import br.com.wgengenharia.manager.model.Client;
import br.com.wgengenharia.manager.model.Company;
import br.com.wgengenharia.manager.model.Employee;
import br.com.wgengenharia.manager.model.Module;
import br.com.wgengenharia.manager.model.Product;
import br.com.wgengenharia.manager.model.Sale;

@ManagedBean(name="manager")
@SessionScoped
public class ManagerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//PRODUCT 
	private ProductBO productBO;
	private Product newProduct;
	private Product selectedProduct;
	private Category selectedCategoryPrd;
	private List<Product> products;
	private List<Product> filteredProducts;
	private List<Product> filteredProductsStock;
	private Integer idCategProdct;
	private String globalFilterProduct;
	private String globalFilterProductStock;
	//CARD 
	private CardBO cardBO;
	private Card selectedCard;
	private List<Card> cards;
	//CATEGORY
	private CategoryBO categoryBO;
	private Category newCategory;
	private Category selectedCategory;
	private List<Category> categories;
	private List<Category> filteredCategories;
	private String globalFilterCategory;
	//CLIENT
	private ClientBO clientBO;
	private Client newClient;
	private List<Client> clients;
	//CASHIER
	private Integer	quantityProduct;
	private Product selectedProductCash;
	private Card selectedCardCash;
	private List<Card> filteredCardCash;
	private String globalFilterCardCash;
	private List<Sale> salesDay;
	//SALES
	private SaleBO saleBO;
	
	//DEFAULT
	public EntityManager em;
	
	public ManagerBean() {
		//new no BO
		try {
			em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			clients = new ArrayList<>();
			
			productBO = new ProductBO(em);
			cardBO = new CardBO(em);
			categoryBO = new CategoryBO(em);
			clientBO = new ClientBO(em);
			saleBO = new SaleBO(em);
			
			newProduct = new Product();
			newCategory = new Category();
			newClient = new Client();
			
			//Carrega as informacoes do banco
			products = productBO.listProducts();
			categories = categoryBO.listCategories();
			cards = cardBO.listCards();
			salesDay = saleBO.listSalesDay(Calendar.getInstance());
			
			
			
			// carrega para tela de produto 
			selectedCategoryPrd = new Category();
			
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
			FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Produto Inserido com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgProduct", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
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
	
	public void newProduct(){
		this.newProduct = new Product();
	}
	
	
	//Metodos Comanda
	public void addCard(){
		try {
			Card card = new Card();
			cardBO.insert(card);
			cards = cardBO.listCards();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCard", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
  
 public void onRowSelectCard(SelectEvent event) {
	 selectedCard = (Card) event.getObject();
 }
 
 public void delCard(){
	 try {
			if(selectedCard!=null){
				cardBO.delete(selectedCard);
				cards = cardBO.listCards();
				selectedCard = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgCard", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Comanda Excluida com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgCard", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar uma Comanda"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCard", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
 }
 
 public void cleanCard(){
	 selectedCard.clear();
 }
 
	
//Metodos Categoria
	public void addCategory(){
		try {
			categoryBO.insert(newCategory);
			categories =  categoryBO.listCategories();
			newCategory = new Category();
			FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Categoria Inserida com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
  
  public void onRowSelectCategory(SelectEvent event) {
	 selectedCategory= (Category) event.getObject();
  }

	public void updateCategory(){
		try {
			if(selectedCategory != null){
				categoryBO.update(selectedCategory);
				categories = categoryBO.listCategories();
				selectedCategory = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Categoria Atualizada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Nescessário selecionar uma Categoria"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void delCategory(){
		try {
			if(selectedCategory != null){
				categoryBO.delete(selectedCategory);
				categories = categoryBO.listCategories();
				selectedCategory = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Categoria Excluida com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Nescessário selecionar uma Categoria"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCategory", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void newCategory(){
		this.newCategory = new Category();
	}
	
	//Metodos Cliente
	public void addClient(){
		try {
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
	
	
	
	// Metodos para o CAIXA
	
	public void addProductCard(Product prod){
		try {
			selectedCardCash.addProduct(prod,1);
			RequestContext.getCurrentInstance().update("formManager:pngListProdCard");
			RequestContext.getCurrentInstance().update("formManager:dtbCardCash");
			RequestContext.getCurrentInstance().update("formManager:pngFindProTab");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addListProductCard(){
		if(quantityProduct > 0 && quantityProduct != null && !"".equals(quantityProduct)){
			if(quantityProduct > selectedProductCash.getQuantity()){
				FacesContext.getCurrentInstance().addMessage("formManager:growlDlgProductQtde", new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Quantidade maior do que a permitida"));
			}else{
				try {
					selectedCardCash.addProduct(selectedProductCash, quantityProduct);
					quantityProduct = 0;
					selectedCardCash.getListGroupProd();

					RequestContext.getCurrentInstance().update("formManager:pngListProdCard");
					RequestContext.getCurrentInstance().update("formManager:dtbCardCash");
					RequestContext.getCurrentInstance().update("formManager:pngFindProTab");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			FacesContext.getCurrentInstance().addMessage("formManager:growlDlgProductQtde", new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Selecione uma quantidade valida."));
		}
	}

	public void closeCard(){
		try {
			ManagerSaleFacadeInterface manager = ManagerSaleFactory.newInstance(selectedCardCash,em);
			manager.persistSale();
			
			selectedCardCash.clear();
			
			selectedCardCash = null;
			
			FacesContext.getCurrentInstance().addMessage("formManager:msgCashier", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Venda concluida!"));
			
			salesDay =  saleBO.listSalesDay(Calendar.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void teste(){
		try {
			BranchBO bBO = new BranchBO(em);
			Branch b = new Branch();
			b.setName("ITAP DA SERRA");
			
			bBO.insert(b);
			
			ModuleBO mBO = new ModuleBO(em);
			Module m = new Module();
			m.setType("LANCHONETE");
			
			mBO.insert(m);
			
			CompanyBO cBO = new CompanyBO(em);
			Company c = new Company();
			c.setName("WGEngenharia");
			c.addBranch(b);
			c.addModule(m);
			
			cBO.insert(c);
			
			
			EmployeeBO eBO = new EmployeeBO(em);
			Employee e = new Employee();
			e.setName("William");
			e.setUser("admin");
			e.setPass("will00gc");
			e.addBranch(b);
			e.setCompany(c);
			
			
			eBO.insert(e);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	
	
	
	
	
	//get and setters
	
	
	// ## produto ##
	public List<Product> getProducts() {
		return products;
	}
	public List<Product> getFilteredProducts() {
		return filteredProducts;
	}
	public void setFilteredProducts(List<Product> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}
	public List<Product> getFilteredProductsStock() {
		return filteredProductsStock;
	}
	public void setFilteredProductsStock(List<Product> filteredProductsStock) {
		this.filteredProductsStock = filteredProductsStock;
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
	public Category getSelectedCategoryPrd() {
		return selectedCategoryPrd;
	}
	public void setSelectedCategoryPrd(Category selectedCategoryPrd) {
		this.selectedCategoryPrd = selectedCategoryPrd;
	}
	public Integer getIdCategProdct() {
		return idCategProdct;
	}
	public void setIdCategProdct(Integer idCategProdct) {
		this.idCategProdct = idCategProdct;
	}
	public String getGlobalFilterProduct() {
		return globalFilterProduct;
	}
	public void setGlobalFilterProduct(String globalFilterProduct) {
		this.globalFilterProduct = globalFilterProduct;
	}
	public String getGlobalFilterProductStock() {
		return globalFilterProductStock;
	}
	public void setGlobalFilterProductStock(String globalFilterProductStock) {
		this.globalFilterProductStock = globalFilterProductStock;
	}

	// ## Comandas ##
	public List<Card> getCards() {
		return cards;
	}
	public Card getSelectedCard() {
		return selectedCard;
	}
	public void setSelectedCard(Card selectedCard) {
		this.selectedCard = selectedCard;
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
	public List<Category> getFilteredCategories() {
		return filteredCategories;
	}
	public void setFilteredCategories(List<Category> filteredCategories) {
		this.filteredCategories = filteredCategories;
	}
	public String getGlobalFilterCategory() {
		return globalFilterCategory;
	}
	public void setGlobalFilterCategory(String globalFilterCategory) {
		this.globalFilterCategory = globalFilterCategory;
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
	
	//## Cashier ##

	public Integer getQuantityProduct() {
		return quantityProduct;
	}
	public void setQuantityProduct(Integer quantityProduct) {
		this.quantityProduct = quantityProduct;
	}
	public Product getSelectedProductCash() {
		return selectedProductCash;
	}
	public void setSelectedProductCash(Product selectedProductCash) {
		this.selectedProductCash = selectedProductCash;
	}
	public Card getSelectedCardCash() {
		return selectedCardCash;
	}
	public void setSelectedCardCash(Card selectedCardCash) {
		this.selectedCardCash = selectedCardCash;
	}
	public List<Card> getFilteredCardCash() {
		return filteredCardCash;
	}
	public void setFilteredCardCash(List<Card> filteredCardCash) {
		this.filteredCardCash = filteredCardCash;
	}
	public String getGlobalFilterCardCash() {
		return globalFilterCardCash;
	}
	public void setGlobalFilterCardCash(String globalFilterCardCash) {
		this.globalFilterCardCash = globalFilterCardCash;
	}
	public List<Sale> getSalesDay() {
		return salesDay;
	}
	public void setSalesDay(List<Sale> salesDay) {
		this.salesDay = salesDay;
	}
}
