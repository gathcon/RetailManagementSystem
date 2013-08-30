package retailManagementSystem;
 
public class Product {
     
    private String productName;
    private String productType;
    private String productQuantity;
    private double productPrice;
    private String productID;
    private int[] stockLevels;
    
<<<<<<< HEAD
    public Product(String productName, String productType, double productPrice, String productBarcode){
=======
    public Product(String productName, String productType, double productPrice, String productQuantity){
>>>>>>> master
    	
        this.productName = productName;
        this.productType = productType;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }
    
<<<<<<< HEAD
    public Product(String productName, String productType, String productQuantity, double productPrice, String productBarcode){
=======
    public Product(String productName, String productType, String productQuantity, double productPrice, String productID){
>>>>>>> master
    	
        this.productName = productName;
        this.productType = productType;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
<<<<<<< HEAD
        this.productID = productBarcode;
=======
        this.productID = productID;
>>>>>>> master
    }
 
    public String getProductName() {
        return productName;
    }
 
    public void setProductName(String productName) {
        this.productName = productName;
    }
     
    public String getProductType() {
        return productType;
    }
 
    public void setProductType(String productType) {
        this.productType = productType;
    }
 
    public String getProductQuantity() {
        return productQuantity;
    }
 
    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }
 
    public double getProductPrice() {
        return productPrice;
    }
 
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
 
    public String getProductID() {
        return productID;
    }
 
    public void setProductID(String productID) {
        this.productID = productID;
    }
    
    public int[] getStockLevels() {
    	return stockLevels;
    }
 
    public void setStockLevels(int[] stockLevels) {
    	this.stockLevels = stockLevels;
    }
}