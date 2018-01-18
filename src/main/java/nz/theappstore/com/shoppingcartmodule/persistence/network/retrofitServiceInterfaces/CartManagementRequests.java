package nz.theappstore.com.shoppingcartmodule.persistence.network.retrofitServiceInterfaces;


import java.util.List;

import nz.theappstore.com.shoppingcartmodule.uiElements.util.SampleProductEntity;
import retrofit2.Call;
import retrofit2.http.GET;
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

}
