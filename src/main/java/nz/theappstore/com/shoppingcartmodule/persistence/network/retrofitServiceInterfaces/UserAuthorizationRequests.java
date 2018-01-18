package nz.theappstore.com.shoppingcartmodule.persistence.network.retrofitServiceInterfaces;


import nz.theappstore.com.shoppingcartmodule.persistence.UserEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by vyomkeshjha on 10/21/17.
 */

public interface UserAuthorizationRequests {
    @POST("login")
    Call<UserEntity> authorizeUser(@Body UserEntity user);

    @POST("logout")
    Call<UserEntity> unauthorizeUser(@Body UserEntity user);

}
