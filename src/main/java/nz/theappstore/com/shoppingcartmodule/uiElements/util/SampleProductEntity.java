package nz.theappstore.com.shoppingcartmodule.uiElements.util;



/**
 * Created by vyomkeshjha on 8/18/17.
 */
public class SampleProductEntity {

    private int productId;
    private String productName;
    private String productDescription;
    private float productPrice;

    public SampleProductEntity(int productId, String productName, String productDescription, float productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public SampleProductEntity(String productName, String productDescription, float productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }


}
