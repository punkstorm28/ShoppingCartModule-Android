package nz.theappstore.com.shoppingcartmodule.persistence.network.retrofitServiceInterfaces;


import java.util.List;

import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by vyomkeshjha on 10/26/17.
 */

public interface CartManagementRequests {

    @GET("/products")
    Observable<List<SampleProductEntity>> getProductList();


    @GET("/products/{product_id}/")
    Observable<SampleProductEntity> getProductById(@Path("product_id") int productId);

    @GET("/products/cart/{session_id}")
    Observable<List<SampleProductEntity>> getCurrentCart(@Path("session_id") int sessionId);

    /**
     * Returns a list of product entities in the cart
     * */
    @PUT("products/cart/{session_id}/{product_id}/add")
    Observable<List<SampleProductEntity>> addItemToCart(@Path("session_id") int sessionId,
                                                  @Path("product_id") int productId);

    @PUT("products/cart/{session_id}/{product_id}/remove")
    Observable<List<SampleProductEntity>> removeItemFromCart(@Path("session_id") int sessionId,
                                                        @Path("product_id") int productId);

}
