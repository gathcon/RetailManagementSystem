package retailManagementSystem;

import java.util.ArrayList;

public class Order {

	private String orderID;
	private String orderDate;
	private String orderDeliveryDate;
	private String orderCost;
	private String orderQuantity;
	private String orderComment;
	
	private boolean isOrderOutstanding;
	
	private ArrayList<Product> products = new ArrayList<Product>();
	
	private Supplier supplier;
	
	public Order(String orderID, Supplier supplier, String orderDate, String orderDeliveryDate, String orderCost, boolean isOrderOutstanding, 
			ArrayList<Product> products, String orderComment){
		
		this.orderID = orderID;
		this.supplier = supplier;
		this.orderDate = orderDate;
		this.orderDeliveryDate = orderDeliveryDate;
		this.orderCost = orderCost;
		this.isOrderOutstanding = isOrderOutstanding;
		this.products = products;
		this.orderComment = orderComment;
	}
	
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getOrderDeliveryDate() {
		return orderDeliveryDate;
	}

	public void setOrderDeliveryDate(String orderDeliveryDate) {
		this.orderDeliveryDate = orderDeliveryDate;
	}

	public String getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(String orderCost) {
		this.orderCost = orderCost;
	}

	public boolean isOrderOutstanding() {
		
		return isOrderOutstanding;
	}

	public void setOrderOutstanding(boolean isOrderOutstanding) {
		this.isOrderOutstanding = isOrderOutstanding;
	}

	public String getOrderDate() {
		
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		
		this.orderDate = orderDate;
	}

	public String getOrderDescription() {
		
		return orderComment;
	}

	public void setOrderDescription(String orderComment) {
		
		this.orderComment = orderComment;
	}
	
	public void addProductToOrder(Product product) {
		
		products.add(product);
	}
	
	public void removeProductFromOrder(Product product) {
		
		products.remove(product);
	}
}

