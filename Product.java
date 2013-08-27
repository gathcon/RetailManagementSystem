package retailManagementSystem;
 
public class Product {
     
    private String productName;
    private String productType;
    private String productQuantity;
    private String productPrice;
    private String productID;
    
    public Product(String productName, String productType, String productPrice, String productBarcode){
    	
        this.productName = productName;
        this.productType = productType;
        this.productQuantity = null;
        this.productPrice = productPrice;
        this.productID = productBarcode;
    }
    
    public Product(String productName, String productType, String productQuantity, String productPrice, String productBarcode){
    	
        this.productName = productName;
        this.productType = productType;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productID = productBarcode;
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
 
    public String getProductPrice() {
        return productPrice;
    }
 
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
 
    public String getProductID() {
        return productID;
    }
 
    public void setProductID(String productID) {
        this.productID = productID;
    }   
 
}